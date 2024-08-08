package com.micro.pe.mfo.service;

import com.micro.pe.mfo.entity.Transaction;
import com.micro.pe.mfo.entity.Limit;
import com.micro.pe.mfo.repository.TransactionRepository;
import com.micro.pe.mfo.repository.LimitRepository;
import com.micro.pe.mfo.service.ExchangeRateService;
import com.micro.pe.mfo.service.TransactionService;
import com.micro.pe.mfo.service.LimitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private LimitService limitService;

    @Mock
    private ExchangeRateService exchangeRateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal("500"));
        transaction.setCurrency("KZT");
        transaction.setDate(LocalDateTime.now());
        transaction.setCategory("Goods");

        Limit limit = new Limit();
        limit.setAmount(new BigDecimal("1000"));
        limit.setCategory("Goods");
        limit.setStartDate(LocalDate.now());

        when(exchangeRateService.getExchangeRate("KZT", "USD", transaction.getDate()))
                .thenReturn(new BigDecimal("0.0023"));
        when(limitService.getLatestLimitForCategory("Goods")).thenReturn(limit);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.saveTransaction(transaction);

        assertEquals(transaction, result);
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    public void testGetExceededTransactions() {
        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal("1500"));
        transaction.setCurrency("KZT");
        transaction.setDate(LocalDateTime.now());
        transaction.setCategory("Goods");
        transaction.setLimitExceeded(true);

        when(transactionRepository.findByLimitExceededTrue()).thenReturn(List.of(transaction));

        List<Transaction> result = transactionService.getExceededTransactions();

        assertEquals(1, result.size());
        assertEquals(transaction, result.get(0));
        verify(transactionRepository, times(1)).findByLimitExceededTrue();
    }
}
