package com.blue.bank.blue_bank.application.facade;
import com.blue.bank.blue_bank.application.port.in.GetAccountUseCase;
import com.blue.bank.blue_bank.application.port.in.GetTransactionByAccountUseCase;
import com.blue.bank.blue_bank.application.query.DashBoardReadModel;
import com.blue.bank.blue_bank.application.query.GetAccountStatementQuery;
import com.blue.bank.blue_bank.application.query.TransactionReadModel;
import com.blue.bank.blue_bank.domain.model.account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AccountDashboardFacade {
    private final GetAccountUseCase getAccountUseCase;
    private final GetTransactionByAccountUseCase getTransactionByAccountUseCase;
    public DashBoardReadModel getDashBoard(Long accountId){
        Account account = getAccountUseCase.findById(accountId);
        List<TransactionReadModel> transactions = getTransactionByAccountUseCase
                .getByAccountId(new GetAccountStatementQuery(accountId));
        return DashBoardReadModel.builder()
                .accountId(account.getId())
                .ownerName(account.getOwnerName())
                .type(account.getType().name())
                .balance(account.getBalance().getAmount())
                .status(account.getStatus().name())
                .recentTransactions(transactions)
                .build();
    }
}