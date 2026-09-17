package com.blue.bank.blue_bank.infrastructure.adapter.listener;
import com.blue.bank.blue_bank.domain.event.TransactionExecutedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
@Component
@Slf4j
public class AuditListener {
    @EventListener
    public void onTransactionExecute(TransactionExecutedEvent event){
        log.info("Registrando auditoría - {} de cuenta #{} a cuenta #{} por ${}",
                event.type(), event.sourceAccountId(), event.targetAccountId(), event.amount());
    }
}