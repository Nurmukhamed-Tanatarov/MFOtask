package com.micro.pe.mfo.service;

import com.micro.pe.mfo.entity.Transaction;
import com.micro.pe.mfo.repository.TransactionRepository;
import org.hibernate.query.spi.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private LimitService limitService;

    @Autowired
    private ExchangeRateService exchangeRateService;

    public Transaction saveTransaction(Transaction transaction) {
        // Рассчитываем сумму в USD
        BigDecimal amountInUSD = convertToUSD(transaction.getAmount(), transaction.getCurrency(), transaction.getDate());

        // Проверяем, превышает ли сумма лимит
        boolean isLimitExceeded = checkLimitExceeded(amountInUSD, transaction.getCategory());
        transaction.setLimitExceeded(isLimitExceeded);

        return transactionRepository.save(transaction);
    }

    private BigDecimal convertToUSD(BigDecimal amount, String currency, LocalDateTime date) {
        BigDecimal rate = exchangeRateService.getExchangeRate(currency, "USD", date);
        return amount.multiply(rate);
    }

    private boolean checkLimitExceeded(BigDecimal amountInUSD, String category) {
        Limit limit = limitService.getLatestLimitForCategory(category);
        if (limit == null) {
            limit = new Limit(); // Используем значение по умолчанию, если лимит не установлен
            limit.setAmount(new BigDecimal("1000"));
        }
        return amountInUSD.compareTo(limit.getAmount()) > 0;
    }

    public List<Transaction> getExceededTransactions() {
        return transactionRepository.findByLimitExceededTrue();
    }
}
