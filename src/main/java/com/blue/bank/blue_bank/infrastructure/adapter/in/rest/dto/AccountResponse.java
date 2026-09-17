package com.blue.bank.blue_bank.infrastructure.adapter.in.rest.dto;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class AccountResponse {
    private Long id;
    private String accountNumber;
    private String ownerName;
    private String email;
    private String type; 
    private BigDecimal balance;
    private String status; 
    private LocalDateTime createdAt;
}