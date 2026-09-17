package com.blue.bank.blue_bank.infrastructure.adapter.out.persistence;
import com.blue.bank.blue_bank.domain.model.shared.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.math.RoundingMode;
@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Money {
    @Column(nullable = false)
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 3)
    private Currency currency;
    private Money(BigDecimal amount, Currency currency) {
        if(amount==null){
            throw new IllegalStateException("El monto no puede ser nulo");
        }
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.currency = currency;
    }
    public static Money of(BigDecimal amount, Currency currency){
        return new Money(amount, currency);
    }
    public static Money zero (Currency currency){
        return new Money(BigDecimal.ZERO, currency);
    }
    public Money add(Money other){
        validateSameCurrency(other);
        return  Money.of(this.amount.add(other.amount), this.currency);
    }
    public Money substract(Money other){
        return Money.of(this.amount.subtract(other.amount), this.currency);
    }
    public boolean isGreatherThan(Money other){
        validateSameCurrency(other);
        return this.amount.compareTo(other.amount) > 0;
    }
    public boolean isLessThan(Money other){
        validateSameCurrency(other);
        return this.amount.compareTo(other.amount) < 0;
    }
    public boolean isNegative(){
        return this.amount.compareTo(BigDecimal.ZERO) < 0;
    }
    private void validateSameCurrency(Money other){
        if(currency != other.currency){
            throw new IllegalArgumentException("No se puede operar montos en monedas distintas: "
            + this.currency + " vs " + other.currency);
        }
    }
    @Override
    public String toString() {
        return amount.toPlainString() + " " + currency;
    }
}