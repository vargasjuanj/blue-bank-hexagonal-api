package com.blue.bank.blue_bank.infrastructure.adapter.in.rest.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public class TransactionResponse {
    private  Long id;
    private  String type;
    private  Long sourceAccountId;
    private  Long targetAccountId;
    private  BigDecimal amount;
    private  BigDecimal fee;
    private  String status;
    private  LocalDateTime createdAt;
    public TransactionResponse(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.sourceAccountId = builder.sourceAccountId;
        this.targetAccountId = builder.targetAccountId;
        this.amount = builder.amount;
        this.fee = builder.fee;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
    }
    public static Builder builder() {
        return new Builder();
    }
    public Long getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public Long getSourceAccountId() {
        return sourceAccountId;
    }
    public Long getTargetAccountId() {
        return targetAccountId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public BigDecimal getFee() {
        return fee;
    }
    public String getStatus() {
        return status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public static class Builder {
        private Long id;
        private String type;
        private Long sourceAccountId;
        private Long targetAccountId;
        private BigDecimal amount;
        private BigDecimal fee;
        private String status;
        private LocalDateTime createdAt;
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder type(String type) {
            this.type = type;
            return this;
        }
        public Builder sourceAccountId(Long sourceAccountId) {
            this.sourceAccountId = sourceAccountId;
            return this;
        }
        public Builder targetAccountId(Long targetAccountId) {
            this.targetAccountId = targetAccountId;
            return this;
        }
        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }
        public Builder fee(BigDecimal fee) {
            this.fee = fee;
            return this;
        }
        public Builder status(String status) {
            this.status = status;
            return this;
        }
        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public TransactionResponse build() {
            return new TransactionResponse(this);
        }
    }
}