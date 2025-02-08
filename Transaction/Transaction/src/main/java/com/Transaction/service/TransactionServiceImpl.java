package com.Transaction.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
	    public List<Transaction> getTransactionsByDate(LocalDate date) {
	        return repository.getTransactionsByDate(date);
	    }

	 public List<Transaction> getTransactionsSortedByDate(@RequestParam("order") String order) {
		 return repository.getTransactionsSortedByDate(order);
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
	    public int getTotalAmountByDate(LocalDate date) {
	        // 날짜별 총액을 가져오는 메서드
	        return repository.getTotalAmountByDate(date);
	    }
	
	public static void main(String[] args) {
		System.out.println("서비스 호출");
	}

}
