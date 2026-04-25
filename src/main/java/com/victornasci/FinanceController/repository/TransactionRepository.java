package com.victornasci.FinanceController.repository;

import com.victornasci.FinanceController.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {


    
}
