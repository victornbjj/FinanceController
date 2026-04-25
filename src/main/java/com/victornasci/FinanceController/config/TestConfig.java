package com.victornasci.FinanceController.config;

import com.victornasci.FinanceController.entity.Transaction;
import com.victornasci.FinanceController.entity.enums.TransactionType;
import com.victornasci.FinanceController.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;




@Component
@Profile("test")
public class TestConfig  implements CommandLineRunner {

    private final TransactionRepository repository;


    public TestConfig(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        Transaction t1 = new Transaction();
        t1.setDescription("Salário");
        t1.setAmount(new BigDecimal("3000"));
        t1.setType(TransactionType.INCOME);
        t1.setCategory("Trabalho");
        t1.setDate(LocalDate.now());
        t1.setInstant(Instant.now());

        Transaction t2 = new Transaction();
        t2.setDescription("Mercado");
        t2.setAmount(new BigDecimal("500"));
        t2.setType(TransactionType.EXPENSE);
        t2.setCategory("Alimentação");
        t2.setDate(LocalDate.now());
        t2.setInstant(Instant.now());



        Transaction t3 = new Transaction();
        t3.setDescription("Freelance");
        t3.setAmount(new BigDecimal("1500"));
        t3.setType(TransactionType.INCOME);
        t3.setCategory("Extra");
        t3.setDate(LocalDate.now());
        t3.setInstant(Instant.now());

        repository.saveAll(List.of(t1, t2, t3));
    }
}
