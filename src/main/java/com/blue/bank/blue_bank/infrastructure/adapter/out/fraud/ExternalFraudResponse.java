package com.blue.bank.blue_bank.infrastructure.adapter.out.fraud;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class ExternalFraudResponse {
    private String riskLevel; 
    private double score; 
    private String recommendation; 
}