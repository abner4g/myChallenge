package com.challengepicpay.controller;

import com.challengepicpay.domain.transaction.Transaction;
import com.challengepicpay.dto.TransactionDTO;
import com.challengepicpay.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {

        Transaction responseTransaction = this.service.createTransaction(transactionDTO);

        return new ResponseEntity<>(responseTransaction, HttpStatus.OK);
    }
}
