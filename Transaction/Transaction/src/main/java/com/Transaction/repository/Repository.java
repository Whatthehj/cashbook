package com.Transaction.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import com.Transaction.entity.Transaction;


@Mapper
public interface Repository {

	@Insert("INSERT INTO MyTransaction (description, amount, date) VALUES (#{description}, #{amount}, #{date})")
	void addTransaction(Transaction transaction);
	
	@Select("SELECT * FROM MyTransaction ORDER BY date desc")
    List<Transaction> getAllTransactions();
	
	@Select("SELECT * FROM MyTransaction ORDER BY date ${order}")
	List<Transaction> getTransactionsSortedByDate(@Param("order") String order);
	
	@Delete("delete FROM MyTransaction WHERE id= #{id}")
	 void deleteTransactionById(Long id);
	
	 @Select("SELECT COALESCE(SUM(amount), 0) FROM MyTransaction WHERE DATE(date) = #{date}")
	    int getTotalAmountByDate(@Param("date") LocalDate date);
	 
	 @Select("SELECT * FROM MyTransaction WHERE DATE(date) = #{date}")
	    List<Transaction> getTransactionsByDate(@Param("date") LocalDate date);

}
