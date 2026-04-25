package com.victornasci.FinanceController.dto;

import com.victornasci.FinanceController.entity.Transaction;
import com.victornasci.FinanceController.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class TransactionResponseDTO {

    private UUID id;
    private String description;
    private BigDecimal amount;
    private TransactionType type;
    private String category;
    private LocalDate date;
    private Instant instant;

    public TransactionResponseDTO(Transaction t) {
        this.id = t.getId();
        this.description = t.getDescription();
        this.amount = t.getAmount();
        this.type = t.getType();
        this.category = t.getCategory();
        this.date = t.getDate();
        this.instant = t.getInstant();
    }

    public UUID getId() { return id; }
    public String getDescription() { return description; }
    public BigDecimal getAmount() { return amount; }
    public TransactionType getType() { return type; }
    public String getCategory() { return category; }
    public LocalDate getDate() { return date; }
    public Instant getInstant() { return instant; }
}
