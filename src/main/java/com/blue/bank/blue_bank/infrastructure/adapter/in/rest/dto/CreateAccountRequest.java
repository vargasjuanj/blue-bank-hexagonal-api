package com.blue.bank.blue_bank.infrastructure.adapter.in.rest.dto;
import com.blue.bank.blue_bank.domain.model.account.AccountType;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;
public class CreateAccountRequest {
    @NotBlank(message = "El número de cuenta es obligatorio")
    private String accountNumber;
    @NotBlank(message = "El nombre del titular es obligatorio")
    private String ownerName;
    @NotBlank
    @Email(message = "El email no tiene un formato válido")
    private String email;
    @NotNull(message = "El tipo de cuenta es obligatorio")
    private AccountType type;
    @PositiveOrZero(message = "El balance  no puede ser negativo")
    private BigDecimal balance;
    public CreateAccountRequest() {
    }
    public CreateAccountRequest(String accountNumber, String ownerName, String email, AccountType type, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.email = email;
        this.type = type;
        this.balance = balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public AccountType getType() {
        return type;
    }
    public void setType(AccountType type) {
        this.type = type;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreateAccountRequest that = (CreateAccountRequest) o;
        return Objects.equals(accountNumber, that.accountNumber) && Objects.equals(ownerName, that.ownerName) && Objects.equals(email, that.email) && Objects.equals(type, that.type) && Objects.equals(balance, that.balance);
    }
    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, ownerName, email, type, balance);
    }
}