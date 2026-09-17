package com.blue.bank.blue_bank.application.service;
import com.blue.bank.blue_bank.application.port.in.GetTransactionByAccountUseCase;
import com.blue.bank.blue_bank.application.port.out.TransactionRepositoryPort;
import com.blue.bank.blue_bank.application.query.GetAccountStatementQuery;
import com.blue.bank.blue_bank.application.query.TransactionReadModel;
import com.blue.bank.blue_bank.domain.model.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TransactionQueryServer implements GetTransactionByAccountUseCase {
    private final TransactionRepositoryPort transactionRepository;
    @Override
    public List<TransactionReadModel> getByAccountId(GetAccountStatementQuery query) {
        return transactionRepository
                .findBySourceAccountIdOrTargetAccountId(query.accountId(), query.accountId())
                .stream()
                .map(this::toReadModel)
                .toList();
    }
    private TransactionReadModel toReadModel(Transaction transaction){
        return new TransactionReadModel(
                transaction.getId(),
                transaction.getType().name(),
                transaction.getSourceAccountId(),
                transaction.getTargetAccountId(),
                transaction.getAmount(),
                transaction.getFee(),
                transaction.getStatus().name(),
                transaction.getCreatedAt()
        );
    }
}