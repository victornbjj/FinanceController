package com.victornasci.FinanceController;

import com.victornasci.FinanceController.dto.TransactionRequestDTO;
import com.victornasci.FinanceController.dto.TransactionResponseDTO;
import com.victornasci.FinanceController.dto.TransactionSummaryDTO;
import com.victornasci.FinanceController.entity.Transaction;
import com.victornasci.FinanceController.entity.enums.TransactionType;
import com.victornasci.FinanceController.exception.ResourceNotFoundException;
import com.victornasci.FinanceController.repository.TransactionRepository;
import com.victornasci.FinanceController.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private TransactionService service;

    private Transaction sampleTransaction;
    private UUID sampleId;

    @BeforeEach
    void setUp() {
        sampleId = UUID.randomUUID();
        sampleTransaction = new Transaction();
        sampleTransaction.setId(sampleId);
        sampleTransaction.setDescription("Salário");
        sampleTransaction.setAmount(new BigDecimal("3000"));
        sampleTransaction.setType(TransactionType.INCOME);
        sampleTransaction.setCategory("Trabalho");
        sampleTransaction.setDate(LocalDate.now());
    }

    @Test
    void shouldSaveTransaction() {
        when(repository.save(any(Transaction.class))).thenReturn(sampleTransaction);

        TransactionRequestDTO dto = new TransactionRequestDTO();
        dto.setDescription("Salário");
        dto.setAmount(new BigDecimal("3000"));
        dto.setType(TransactionType.INCOME);
        dto.setCategory("Trabalho");
        dto.setDate(LocalDate.now());

        TransactionResponseDTO result = service.save(dto);

        assertNotNull(result);
        assertEquals("Salário", result.getDescription());
        verify(repository, times(1)).save(any(Transaction.class));
    }

    @Test
    void shouldThrowWhenTransactionNotFound() {
        when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(UUID.randomUUID()));
    }

    @Test
    void shouldCalculateSummaryCorrectly() {
        when(repository.sumByType(TransactionType.INCOME)).thenReturn(new BigDecimal("3000"));
        when(repository.sumByType(TransactionType.EXPENSE)).thenReturn(new BigDecimal("1000"));

        TransactionSummaryDTO summary = service.getSummary();

        assertEquals(0, new BigDecimal("3000").compareTo(summary.getIncome()));
        assertEquals(0, new BigDecimal("1000").compareTo(summary.getExpense()));
        assertEquals(0, new BigDecimal("2000").compareTo(summary.getBalance()));
    }

    @Test
    void shouldUpdateDateOnUpdate() {
        when(repository.findById(sampleId)).thenReturn(Optional.of(sampleTransaction));
        when(repository.save(any(Transaction.class))).thenReturn(sampleTransaction);

        LocalDate newDate = LocalDate.of(2025, 1, 15);
        TransactionRequestDTO dto = new TransactionRequestDTO();
        dto.setDescription("Salário atualizado");
        dto.setAmount(new BigDecimal("3500"));
        dto.setType(TransactionType.INCOME);
        dto.setCategory("Trabalho");
        dto.setDate(newDate);

        service.update(sampleId, dto);

        assertEquals(newDate, sampleTransaction.getDate());
        assertEquals("Salário atualizado", sampleTransaction.getDescription());
    }
}
