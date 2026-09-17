package com.blue.bank.blue_bank.application.service;
import com.blue.bank.blue_bank.application.port.out.TransactionRepositoryPort;
import com.blue.bank.blue_bank.domain.model.transaction.Transaction;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
public abstract class TransactionProcessor<C> {
    protected final TransactionRepositoryPort transactionRepository;
    protected TransactionProcessor(TransactionRepositoryPort transactionRepository){
        this.transactionRepository = transactionRepository;
    }
    @Transactional
    public Transaction process(C ctx) {
        validate(ctx);
        BigDecimal fee = calculateFee(ctx);
        execute(ctx, fee);
        return save(ctx, fee);
    }
    protected abstract void validate(C ctx);
    protected abstract BigDecimal calculateFee(C ctx);
    protected abstract void execute(C ctx, BigDecimal fee);
    protected abstract Transaction save(C ctx, BigDecimal fee);
}