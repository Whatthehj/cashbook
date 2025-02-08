package com.Transaction.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Transaction.entity.Transaction;

public interface TransactionService {
	List<Transaction> getAllTransactions();
	List<Transaction> getTransactionsByDate(LocalDate date);
	List<Transaction> getTransactionsSortedByDate(@Param("order") String order);
	
	int getTotalAmountByDate(LocalDate date);
	
	void deleteTransactionById(Long id);
	void addTransaction(Transaction transaction);
	
}
