package com.micro.pe.mfo.dto;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class TransactionDTO {
    private Integer amount;
    private String currency;
    private String category;
    private Timestamp transactionDate;
    private boolean limit_exceeded;
    private Timestamp createdAt;
}
