package com.blue.bank.blue_bank.infrastructure.adapter.out.persistence;
import com.blue.bank.blue_bank.application.port.out.TransactionRepositoryPort;
import com.blue.bank.blue_bank.domain.model.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class JpaTransactionRepositoryAdaptar implements TransactionRepositoryPort {
    private final SpringDataTransactionRepository jpaRepository;
    private final TransactionPersistenceMapper mapper;
    private final ApplicationEventPublisher eventPublisher;
    @Override
    public Transaction save(Transaction transaction) {
        transaction.initDefaults();
        TransactionJpaEntity entity = mapper.toJpaEntity(transaction);
        TransactionJpaEntity saved = jpaRepository.save(entity);
        Transaction result = mapper.toDomain(saved);
        transaction.getDomainEvents().forEach(eventPublisher::publishEvent);
        transaction.clearDomainEvents();
        return result;
    }
    @Override
    public List<Transaction> findBySourceAccountIdOrTargetAccountId(Long sourceId, Long targetId) {
        return jpaRepository.findBySourceAccountIdOrTargetAccountId(sourceId, targetId)
                .stream()
                .map(mapper::toDomain).toList();
    }
}