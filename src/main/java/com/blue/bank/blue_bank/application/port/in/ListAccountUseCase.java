package com.blue.bank.blue_bank.application.port.in;
import com.blue.bank.blue_bank.domain.model.account.Account;
import java.util.List;
public interface ListAccountUseCase {
    List<Account> findAll();
}