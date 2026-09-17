package com.blue.bank.blue_bank.infrastructure.adapter.in.rest.dto;
import com.blue.bank.blue_bank.domain.model.account.Account;
import com.blue.bank.blue_bank.domain.model.shared.Email;
import com.blue.bank.blue_bank.domain.model.shared.Money;
import com.blue.bank.blue_bank.domain.model.shared.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.math.BigDecimal;
@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "balance", source = "balance", qualifiedByName = "toMoney")
    @Mapping(target = "email", source = "email", qualifiedByName = "toEmail")
    Account toEntity(CreateAccountRequest request);
    @Mapping(target = "balance", source = "balance", qualifiedByName = "toAmount")
    @Mapping(target = "email", source = "email", qualifiedByName = "fromEmail")
    AccountResponse toResponse(Account account);
    @Named("toMoney")
    default Money toMoney(BigDecimal amount){
        if(amount==null) return null; 
        return Money.of(amount, Currency.ARS);
    }
    @Named("toAmount")
    default BigDecimal toAmount(Money money){
        if (money==null) return null; 
        return money.getAmount();
    }
    @Named("toEmail")
    default Email toEmail(String value){
        if(value==null) return null;
        return Email.of(value);
    }
    @Named("fromEmail")
    default String fromEmail(Email email){
        if (email==null) return null;
        return email.getValue();
    }
}