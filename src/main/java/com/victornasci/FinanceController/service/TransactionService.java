package com.victornasci.FinanceController.service;

import com.victornasci.FinanceController.dto.TransactionRequestDTO;
import com.victornasci.FinanceController.dto.TransactionResponseDTO;
import com.victornasci.FinanceController.dto.TransactionSummaryDTO;
import com.victornasci.FinanceController.entity.Transaction;
import com.victornasci.FinanceController.entity.enums.TransactionType;
import com.victornasci.FinanceController.exception.ResourceNotFoundException;
import com.victornasci.FinanceController.repository.TransactionRepository;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionResponseDTO> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map(TransactionResponseDTO::new)
                .toList();
    }

    public TransactionResponseDTO findById(UUID id) {
        Transaction entity = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        return new TransactionResponseDTO(entity);
    }

    @Transactional
    public TransactionResponseDTO save(TransactionRequestDTO dto) {
        Transaction entity = new Transaction();
        entity.setDescription(dto.getDescription());
        entity.setAmount(dto.getAmount());
        entity.setType(dto.getType());
        entity.setCategory(dto.getCategory());
        entity.setDate(dto.getDate());
        return new TransactionResponseDTO(transactionRepository.save(entity));
    }

    @Transactional
    public TransactionResponseDTO update(UUID id, TransactionRequestDTO dto) {
        Transaction entity = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));

        entity.setDescription(dto.getDescription());
        entity.setAmount(dto.getAmount());
        entity.setType(dto.getType());
        entity.setCategory(dto.getCategory());
        entity.setDate(dto.getDate()); // corrigido: campo date agora é atualizado

        return new TransactionResponseDTO(transactionRepository.save(entity));
    }

    @Transactional
    public void deleteById(UUID id) {
        Transaction entity = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        transactionRepository.delete(entity);
    }

    public TransactionSummaryDTO getSummary() {
        BigDecimal income = transactionRepository.sumByType(TransactionType.INCOME);
        BigDecimal expense = transactionRepository.sumByType(TransactionType.EXPENSE);
        BigDecimal balance = income.subtract(expense);
        return new TransactionSummaryDTO(income, expense, balance);
    }
}
