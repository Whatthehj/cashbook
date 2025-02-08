package com.Transaction.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Transaction.entity.Transaction;
import com.Transaction.service.TransactionService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")

public class RestController {
	  private final TransactionService transactionService;

	    public RestController(TransactionService transactionService) {
	        this.transactionService = transactionService;
	    }

	    @GetMapping("/transactions")
	    public List<Transaction> getTransactions() {
	        return transactionService.getAllTransactions();
	    }
}
