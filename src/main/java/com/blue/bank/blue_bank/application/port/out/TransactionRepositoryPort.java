package com.blue.bank.blue_bank.application.port.out;
import com.blue.bank.blue_bank.domain.model.transaction.Transaction;
import java.util.List;
public interface TransactionRepositoryPort {
    Transaction save(Transaction transaction);
    List<Transaction> findBySourceAccountIdOrTargetAccountId(Long sourceId, Long targetId);
}