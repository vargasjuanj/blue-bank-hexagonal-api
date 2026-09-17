package com.blue.bank.blue_bank.domain.model.account;
import com.blue.bank.blue_bank.domain.exception.InsufficientFundsException;
import com.blue.bank.blue_bank.domain.model.shared.Currency;
import com.blue.bank.blue_bank.domain.model.shared.Email;
import com.blue.bank.blue_bank.domain.model.shared.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
class AccountDomainTest {
    private Account createAccountWithBalance(BigDecimal balance){
        return Account.builder()
                .id(1L)
                .accountNumber("ATL-0001-0001-0001")
                .ownerName("Maria Garcia")
                .email(Email.of("maria@blue.com"))
                .type(AccountType.SAVINGS)
                .balance(Money.of(balance, Currency.ARS))
                .status(AccountStatus.ACTIVE)
                .build();
    }
    @Test
    @DisplayName("Debe depositar dinero correctamente")
    void shouldDepositMoney(){
        Account account = createAccountWithBalance(new BigDecimal("1000"));
        Money deposit = Money.of(new BigDecimal("500"), Currency.ARS);
        account.deposit(deposit);
        assertEquals(
                Money.of(new BigDecimal("1500"), Currency.ARS),
                account.getBalance()
        );
    }
    @Test
    @DisplayName("Debe retirar dinero cuando hay fondos suficientes")
    void shouldWithdrawWhenSufficientFunds(){
        Account account = createAccountWithBalance(new BigDecimal("1000"));
        Money withdrawal = Money.of(new BigDecimal("300"), Currency.ARS);
        account.withdraw(withdrawal);
        assertEquals(
                Money.of(new BigDecimal("700"), Currency.ARS),
                account.getBalance()
        );
    }
    @Test
    @DisplayName("Debe rechazar retiro cuando no hay fondos suficientes")
    void shouldRejectWithDrawWhenInsufficientFunds(){
        Account account = createAccountWithBalance(new BigDecimal("100"));
        Money withdrawal = Money.of(new BigDecimal("500"), Currency.ARS);
        assertThrows(
                InsufficientFundsException.class, () ->
                        account.withdraw(withdrawal)
        );
    }
    @Test
    @DisplayName("Debe rechazar deposito con monto negativo")
    void shouldRejectNegativeDeposit(){
        Account account = createAccountWithBalance(new BigDecimal("1000"));
        Money negative = Money.of(new BigDecimal("-100"), Currency.ARS);
        assertThrows(
                IllegalArgumentException.class, () ->
                        account.deposit(negative)
        );
    }
    @Test
    @DisplayName("Debe iniciaizar con valores por defecto")
    void shouldInitializeDefaults(){
        Account account = Account.builder()
                .accountNumber("ATL-0002-0002-0002")
                .ownerName("Juan Perez")
                .email(Email.of("juan@blue.bank"))
                .type(AccountType.CHECKING)
                .build();
        account.initDefaults();
        assertEquals(AccountStatus.ACTIVE, account.getStatus());
        assertEquals(Money.zero(Currency.ARS), account.getBalance());
        assertNotNull(account.getCreatedAt());
    }
}