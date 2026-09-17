package com.blue.bank.blue_bank.application.service;
import com.blue.bank.blue_bank.application.command.TransferMoneyCommand;
import com.blue.bank.blue_bank.application.port.out.TransactionRepositoryPort;
import com.blue.bank.blue_bank.domain.exception.AccountNotFoundExcepcion;
import com.blue.bank.blue_bank.domain.model.account.Account;
import com.blue.bank.blue_bank.application.port.in.TransferMoneyUseCase;
import com.blue.bank.blue_bank.application.port.out.AccountRepositoryPort;
import com.blue.bank.blue_bank.domain.model.transaction.TransferContext;
import com.blue.bank.blue_bank.domain.model.transaction.Transaction;
import com.blue.bank.blue_bank.domain.service.TransferDomainService;
import com.blue.bank.blue_bank.domain.strategy.fee.FeeCalculator;
import com.blue.bank.blue_bank.domain.validation.TransferValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
@Service
public class TransferService extends TransactionProcessor<TransferContext>
        implements  TransferMoneyUseCase {
    private final AccountRepositoryPort accountRepository;
    private final List<FeeCalculator> feeCalculators;
    private final List<TransferValidator> validators;
    private final TransferDomainService transferDomainService;
    public TransferService(TransactionRepositoryPort transactionRepository,
                           AccountRepositoryPort accountRepository,
                           List<FeeCalculator> feeCalculators,
                           List<TransferValidator> validators,
                           TransferDomainService transferDomainService) {
        super(transactionRepository);
        this.accountRepository = accountRepository;
        this.feeCalculators = feeCalculators;
        this.validators = validators;
        this.transferDomainService = transferDomainService;
    }
    @Override
    @Transactional
    public Transaction transfer(TransferMoneyCommand command) {
        Account from = accountRepository.findById(command.fromId())
                .orElseThrow(() -> new AccountNotFoundExcepcion(command.fromId()));
        Account to = accountRepository.findById(command.toId())
                .orElseThrow(() -> new AccountNotFoundExcepcion(command.toId()));
        Transaction transaction = process(new TransferContext(from, to, command.amount()));
        transaction.executeTransfer();
        transactionRepository.save(transaction);
        return transaction;
    }
    @Override
    protected void validate(TransferContext ctx) {
        validators.forEach(validator -> validator.validate(ctx));
    }
    @Override
    protected BigDecimal calculateFee(TransferContext ctx) {
        return feeCalculators.stream()
                .filter(fc -> fc.supports(ctx.from().getType()))
                .findFirst()
                .orElseThrow( () -> new RuntimeException("No hay calculador para el tipo " + ctx.from().getType())) 
                .calculate(ctx.amount());
    }
    @Override
    protected void execute(TransferContext ctx, BigDecimal fee) {
        transferDomainService.transfer(ctx.from(), ctx.to(), ctx.amount(), fee);
        accountRepository.save(ctx.from());
        accountRepository.save(ctx.to());
    }
    @Override
    protected Transaction save(TransferContext ctx, BigDecimal fee) {
        Transaction transaction = TransactionFactory.createTransfer(ctx, fee);
        return transactionRepository.save(transaction);
    }
}