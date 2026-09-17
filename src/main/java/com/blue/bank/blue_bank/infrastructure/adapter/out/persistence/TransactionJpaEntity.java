package com.blue.bank.blue_bank.infrastructure.adapter.out.persistence;
import com.blue.bank.blue_bank.domain.model.transaction.TransactionStatus;
import com.blue.bank.blue_bank.domain.model.transaction.TransactionType;
import com.blue.bank.blue_bank.domain.model.transaction.state.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name= "transactions")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class TransactionJpaEntity extends AbstractAggregateRoot<TransactionJpaEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionType type; 
    @Column(name = "source_account_id")
    private Long sourceAccountId;
    @Column(name = "target_account_id")
    private Long targetAccountId;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private BigDecimal fee;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionStatus status; 
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Transient
    private TransactionState state;
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) this.status = TransactionStatus.EXECUTED;
    }
}