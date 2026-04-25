package com.victornasci.FinanceController;

import com.victornasci.FinanceController.entity.Transaction;
import com.victornasci.FinanceController.entity.enums.TransactionType;
import com.victornasci.FinanceController.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void shouldSaveTransaction() {
        Transaction t = new Transaction();
        t.setDescription("Teste");
        t.setAmount(new BigDecimal("100"));
        t.setType(TransactionType.INCOME);
        t.setCategory("Teste");
        t.setInstant(Instant.now());

        Transaction saved = repository.save(t);

        assertNotNull(saved.getId());
        assertEquals("Teste", saved.getDescription());
    }

    @Test
    void shouldDeleteTransaction() {
        Transaction t = new Transaction();
        t.setDescription("Teste Delete");
        t.setAmount(new BigDecimal("100"));
        t.setType(TransactionType.EXPENSE);
        t.setCategory("Teste");
        t.setInstant(Instant.now());

        Transaction saved = repository.save(t);
        repository.deleteById(saved.getId());

        assertFalse(repository.findById(saved.getId()).isPresent());
    }

    @Test
    void shouldCalculateSummary() {
        Transaction income = new Transaction();
        income.setDescription("Salário teste");
        income.setAmount(new BigDecimal("1000"));
        income.setType(TransactionType.INCOME);
        income.setCategory("Trabalho");
        income.setInstant(Instant.now());

        Transaction expense = new Transaction();
        expense.setDescription("Gasto teste");
        expense.setAmount(new BigDecimal("500"));
        expense.setType(TransactionType.EXPENSE);
        expense.setCategory("Outros");
        expense.setInstant(Instant.now());

        repository.save(income);
        repository.save(expense);

        BigDecimal totalIncome = repository.sumByType(TransactionType.INCOME);
        BigDecimal totalExpense = repository.sumByType(TransactionType.EXPENSE);

        assertEquals(0, new BigDecimal("1000").compareTo(totalIncome));
        assertEquals(0, new BigDecimal("500").compareTo(totalExpense));
    }
}
