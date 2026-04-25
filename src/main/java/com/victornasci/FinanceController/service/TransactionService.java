package com.victornasci.FinanceController.service;

import com.victornasci.FinanceController.entity.Transaction;
import com.victornasci.FinanceController.exception.ResourceNotFoundException;
import com.victornasci.FinanceController.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class TransactionService {



    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }


    public Transaction findById(UUID idTransaction){
        return transactionRepository.findById(idTransaction)
                .orElseThrow(() ->  new ResourceNotFoundException("Transaction not found with id: " + idTransaction));
    }

    @Transactional
    public void deleteById(UUID id){
      Transaction entity = transactionRepository.findById(id)
              .orElseThrow(() ->  new ResourceNotFoundException("Transaction not found with id: " + id));

      transactionRepository.delete(entity);


     }


    public Transaction update(UUID idTransaction, Transaction newData){

        Transaction entity = transactionRepository.findById(idTransaction)
                        .orElseThrow(() ->  new ResourceNotFoundException("Transaction not found with id: " + idTransaction));

        entity.setDescription(newData.getDescription());
        entity.setAmount(newData.getAmount());
        entity.setType(newData.getType());
        entity.setCategory(newData.getCategory());

        return transactionRepository.save(entity);

    }



    public Transaction save(Transaction Transaction){
        return  transactionRepository.save(Transaction);
    }





}
