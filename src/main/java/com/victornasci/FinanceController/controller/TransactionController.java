package com.victornasci.FinanceController.controller;


import com.victornasci.FinanceController.dto.TransactionSummaryDTO;
import com.victornasci.FinanceController.service.TransactionService;
import com.victornasci.FinanceController.entity.Transaction;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/transactions")
public class TransactionController {


    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }



    @PostMapping
    public ResponseEntity<Transaction> create(@Valid @RequestBody  Transaction transaction){
        Transaction saved = service.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    public ResponseEntity<TransactionSummaryDTO> getSummary(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getSummary());
    }


    @GetMapping
    public ResponseEntity<List<Transaction>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById( @PathVariable UUID id){
        Transaction obj = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction>  update(@PathVariable UUID id, @Valid @RequestBody  Transaction transactionDetails){

        Transaction updated = service.update(id, transactionDetails);
        return ResponseEntity.status(HttpStatus.OK).body(updated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }






}
