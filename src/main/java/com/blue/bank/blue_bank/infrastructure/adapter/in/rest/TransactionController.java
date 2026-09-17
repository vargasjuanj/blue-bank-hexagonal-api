package com.blue.bank.blue_bank.infrastructure.adapter.in.rest;
import com.blue.bank.blue_bank.application.command.TransferMoneyCommand;
import com.blue.bank.blue_bank.application.port.in.GetTransactionByAccountUseCase;
import com.blue.bank.blue_bank.application.port.in.TransferMoneyUseCase;
import com.blue.bank.blue_bank.application.query.GetAccountStatementQuery;
import com.blue.bank.blue_bank.application.query.TransactionReadModel;
import com.blue.bank.blue_bank.domain.model.transaction.Transaction;
import com.blue.bank.blue_bank.infrastructure.adapter.in.rest.dto.TransactionMapper;
import com.blue.bank.blue_bank.infrastructure.adapter.in.rest.dto.TransactionResponse;
import com.blue.bank.blue_bank.infrastructure.adapter.in.rest.dto.TransferRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransferMoneyUseCase transferMoneyUseCase;
    private final GetTransactionByAccountUseCase getTransactionByAccountUseCase;
    private final TransactionMapper transactionMapper;
    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transfer (@Valid @RequestBody TransferRequest request){
        TransferMoneyCommand command = TransferMoneyCommand.builder()
                .toId(request.getToAccountId())
                .fromId(request.getFromAccountId())
                .amount(request.getAmount())
                .build();
        Transaction transaction = transferMoneyUseCase.transfer(
                command
        );
        return ResponseEntity.ok(transactionMapper.toResponse(transaction));
    }
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionReadModel>> getTransaction(@PathVariable Long id){
        GetAccountStatementQuery query = new GetAccountStatementQuery(id);
        List<TransactionReadModel> responses = this.getTransactionByAccountUseCase.getByAccountId(query);
        return ResponseEntity.ok(responses);
    }
}