package com.victornasci.FinanceController.entity;

import com.victornasci.FinanceController.entity.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_transaction")
public class Transaction {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @NotBlank
    private String description;


    @NotNull
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

   private  String category;

   private LocalDate date;

   private Instant instant;



    @PrePersist
    public void prePersist(){
        this.instant = Instant.now();
    }




}
