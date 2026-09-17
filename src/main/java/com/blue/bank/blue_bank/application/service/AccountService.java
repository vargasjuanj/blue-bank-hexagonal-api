package com.blue.bank.blue_bank.application.service;
import com.blue.bank.blue_bank.application.command.CloseAccountCommand;
import com.blue.bank.blue_bank.application.command.CreateAccountCommand;
import com.blue.bank.blue_bank.application.port.in.CloseAccountUseCase;
import com.blue.bank.blue_bank.application.port.in.CreateAccountUseCase;
import com.blue.bank.blue_bank.application.port.in.GetAccountUseCase;
import com.blue.bank.blue_bank.application.port.in.ListAccountUseCase;
import com.blue.bank.blue_bank.application.port.out.AccountRepositoryPort;
import com.blue.bank.blue_bank.domain.event.AccountClosedEvent;
import com.blue.bank.blue_bank.domain.exception.AccountNotFoundExcepcion;
import com.blue.bank.blue_bank.domain.model.account.Account;
import com.blue.bank.blue_bank.domain.model.shared.Currency;
import com.blue.bank.blue_bank.domain.model.shared.Email;
import com.blue.bank.blue_bank.domain.model.shared.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AccountService implements CreateAccountUseCase, ListAccountUseCase, GetAccountUseCase, CloseAccountUseCase {
    private final AccountRepositoryPort accountRepository;
    private final ApplicationEventPublisher eventPublisher;
    @Override
    public Account create(CreateAccountCommand command) {
        Account account = Account.builder()
                .accountNumber(command.accountNumber())
                .ownerName(command.ownerName())
                .email(Email.of(command.email()))
                .type(command.type())
                .balance(Money.of(command.balance(), Currency.ARS))
                .build();
        return accountRepository.save(account);
    }
    @Override
    @Transactional
    @CacheEvict(value = "accounts", key = "#command.accountId()")
    public Account close(CloseAccountCommand command) {
        Account account = accountRepository.findById(command.accountId())
                .orElseThrow(() -> new AccountNotFoundExcepcion(command.accountId()));
        account.close();
        Account closed = accountRepository.save(account);
        eventPublisher.publishEvent(new AccountClosedEvent(closed.getId(), closed.getAccountNumber()));
        return closed;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "accounts", key = "#id")
    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundExcepcion(id));
    }
}