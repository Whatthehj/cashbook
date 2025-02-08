package com.Transaction.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Transaction {
    private Long id;             // 거래 ID
    private String description;  // 거래 설명
    private double amount;       // 금액 (양수: 수입, 음수: 지출)
    private LocalDate date;         // 거래 날짜 (YYYY-MM-DD)

    
    
}