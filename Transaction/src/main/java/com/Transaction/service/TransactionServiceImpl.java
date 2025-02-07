package com.Transaction.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Transaction.entity.Transaction;
import com.Transaction.repository.Repository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
	private final Repository repository;

	@Override
	public List<Transaction> getAllTransactions() {
		// TODO Auto-generated method stub
		return repository.getAllTransactions();
	}

	@Override
	public void addTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		repository.addTransaction(transaction);
		
	}

	@Override
	public void deleteTransactionById(Long id) {
		// TODO Auto-generated method stub
		repository.deleteTransactionById(id);
	}
	
	 @Override
	    public BigDecimal getTotalAmountByDate(LocalDate date) {
	        return repository.getTotalAmountByDate(date);
	    }
	
	public static void main(String[] args) {
		System.out.println("서비스 호출");
	}

}
