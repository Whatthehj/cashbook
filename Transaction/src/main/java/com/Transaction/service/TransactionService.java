package com.Transaction.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.Transaction.entity.Transaction;

public interface TransactionService {
	List<Transaction> getAllTransactions();
	
	BigDecimal getTotalAmountByDate(LocalDate date);
	
	void deleteTransactionById(Long id);
	void addTransaction(Transaction transaction);
	
}
