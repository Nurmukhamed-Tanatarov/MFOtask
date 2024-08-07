package com.micro.pe.mfo.service;

import com.micro.pe.mfo.entity.ExchangeRate;
import com.micro.pe.mfo.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ExchangeRateService {
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public BigDecimal getExchangeRate(String fromCurrency, String toCurrency, LocalDateTime date) {
        // Логика для получения курса обмена из базы данных или внешнего источника
        // Если курс на текущую дату недоступен, используйте предыдущий закрытый курс
        ExchangeRate rate = exchangeRateRepository.findLatestRate(fromCurrency, toCurrency, date);
        return rate != null ? rate.getRate() : BigDecimal.ONE;
    }
}
