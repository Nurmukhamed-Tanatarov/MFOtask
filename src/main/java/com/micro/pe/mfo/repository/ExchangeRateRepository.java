package com.micro.pe.mfo.repository;

import com.micro.pe.mfo.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    ExchangeRate findLatestRate(String fromCurrency, String toCurrency, LocalDateTime date);
}
