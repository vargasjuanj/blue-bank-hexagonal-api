package com.blue.bank.blue_bank.application.port.out;
import com.blue.bank.blue_bank.domain.model.account.Account;
import java.util.List;
import java.util.Optional;
public interface AccountRepositoryPort {
    Optional<Account> findById(Long id);
    List<Account> findAll();
    Account save(Account account);
}