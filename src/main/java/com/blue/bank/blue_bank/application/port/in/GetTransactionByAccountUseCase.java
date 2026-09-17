package com.blue.bank.blue_bank.application.port.in;
import com.blue.bank.blue_bank.application.query.GetAccountStatementQuery;
import com.blue.bank.blue_bank.application.query.TransactionReadModel;
import java.util.List;
public interface GetTransactionByAccountUseCase {
    List<TransactionReadModel> getByAccountId(GetAccountStatementQuery query);
}