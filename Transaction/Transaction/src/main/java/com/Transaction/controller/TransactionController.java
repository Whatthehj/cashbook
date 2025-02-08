package com.Transaction.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Transaction.entity.Transaction;
import com.Transaction.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/list")
    public String list(Model model) {
    	model.addAttribute("myList", transactionService.getAllTransactions());
        return "/listPage";
    }
    
    @GetMapping("/add")
    public String newForm(Model model) {
    	model.addAttribute("transaction", new Transaction());
    	return "addPage";
    }
    
//    @GetMapping("/listByDate")
//    public String getTransactionsByDate(
//            @RequestParam("selectedDate") LocalDate date, Model model) {
//        
//        // 해당 날짜에 해당하는 거래 내역을 가져옴
//        List<Transaction> transactions = transactionService.getAllTransactions();
//        
//        // 해당 날짜의 총액 계산
//        Double totalAmount = transactions.stream()
//        		.map(Transaction::getAmount) // Double 값으로 변환
//                .reduce(0.0, Double::sum);
//        
//        // 모델에 데이터 추가
//        model.addAttribute("myList", transactions);
//        model.addAttribute("totalAmount", totalAmount);
//        model.addAttribute("selectedDate", date);
//        
//        return "listPage"; // "list"는 Thymeleaf 템플릿 이름
//    }
    
    @GetMapping("/listByDate")
    public String listByDate(@RequestParam("selectedDate") String selectedDate, Model model) {
        LocalDate date = LocalDate.parse(selectedDate);
        
        // 선택한 날짜에 해당하는 거래 내역 가져오기
        List<Transaction> transactions = transactionService.getTransactionsByDate(date);
        
        // 선택한 날짜에 해당하는 총액 계산하기
        int totalAmount = transactionService.getTotalAmountByDate(date);
        
        // 모델에 데이터 추가
        model.addAttribute("myList", transactions);
        model.addAttribute("totalAmount", totalAmount);  // 선택한 날짜에 해당하는 총액만 전달

        return "listPage";  // 해당 날짜만 표시되는 템플릿을 반환
    }
    
    
    @GetMapping("/transactions")
    public String getTransactions(
            @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
            Model model) {

        // 정렬된 데이터를 가져옴
        List<Transaction> transactions = transactionService.getTransactionsSortedByDate(order);

        // 모델에 데이터 추가
        model.addAttribute("myList", transactions);
        model.addAttribute("currentOrder", order);  // 현재 정렬 상태를 View로 전달

        return "listPage";
    }

    
    
    @PostMapping("/add")
    public String addTransaction(@ModelAttribute Transaction transaction, Model model) {
        try {
            transactionService.addTransaction(transaction);  // 거래 추가
            return "redirect:/main";  // 거래 추가 후 목록 페이지로 리다이렉트
        } catch (Exception e) {
            model.addAttribute("error", "거래 추가 중 오류가 발생했습니다.");
            return "addPage";  // 오류가 발생하면 다시 입력 폼으로 돌아감
        }
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
    	transactionService.deleteTransactionById(id);
        return "redirect:/list";
    }
  
}

