package com.micro.pe.mfo.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private BigDecimal amount;
    private String currency;
    private LocalDateTime date;
    private String category;
    private String description;
    private Boolean limitExceeded;
}
