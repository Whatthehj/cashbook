package com.Transaction.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping("/add")
//    public String showAddTransactionForm(Model model) {
//        model.addAttribute("transaction", new Transaction());  
//        // transaction이라는 이름으로 addPage.html로 전달. ${transaction}으로 접근
//        return "addPage";  // addPage.html을 반환하여 입력 폼을 보여줌
//    }
    
    @GetMapping("/add")
    public String newForm(Model model) {
    	model.addAttribute("transaction", new Transaction());
    	return "addPage";
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

