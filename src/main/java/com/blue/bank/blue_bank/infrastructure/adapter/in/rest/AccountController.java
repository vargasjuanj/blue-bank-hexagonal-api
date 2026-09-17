package com.blue.bank.blue_bank.infrastructure.adapter.in.rest;
import com.blue.bank.blue_bank.application.command.CloseAccountCommand;
import com.blue.bank.blue_bank.application.command.CreateAccountCommand;
import com.blue.bank.blue_bank.application.facade.AccountDashboardFacade;
import com.blue.bank.blue_bank.application.port.in.CloseAccountUseCase;
import com.blue.bank.blue_bank.application.port.in.CreateAccountUseCase;
import com.blue.bank.blue_bank.application.port.in.GetAccountUseCase;
import com.blue.bank.blue_bank.application.port.in.ListAccountUseCase;
import com.blue.bank.blue_bank.application.query.DashBoardReadModel;
import com.blue.bank.blue_bank.infrastructure.adapter.in.rest.dto.AccountMapper;
import com.blue.bank.blue_bank.infrastructure.adapter.in.rest.dto.AccountResponse;
import com.blue.bank.blue_bank.infrastructure.adapter.in.rest.dto.CreateAccountRequest;
import com.blue.bank.blue_bank.domain.model.account.Account;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final CreateAccountUseCase createAccountUseCase;
    private final ListAccountUseCase listAccountUseCase;
    private final GetAccountUseCase getAccountUseCase;
    private final CloseAccountUseCase closeAccountUseCase;
    private final AccountMapper accountMapper;
    private final AccountDashboardFacade dashboardFacade;
    @GetMapping("/{id}/dashboard")
    public ResponseEntity<DashBoardReadModel> getDashboard(@PathVariable Long id){
        return ResponseEntity.ok(dashboardFacade.getDashBoard(id));
    }
    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody CreateAccountRequest request){
        CreateAccountCommand command = CreateAccountCommand.builder()
                .accountNumber(request.getAccountNumber())
                .ownerName(request.getOwnerName())
                .email(request.getEmail())
                .type(request.getType())
                .balance(request.getBalance())
                .build();
        Account saved = createAccountUseCase.create(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountMapper.toResponse(saved));
    }
    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAll(){
        List<AccountResponse> responses = listAccountUseCase.findAll()
                .stream()
                .map(accountMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(accountMapper.toResponse(getAccountUseCase.findById(id)));
    }
    @PatchMapping("/{id}/close")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccountResponse> close(@PathVariable Long id){
        CloseAccountCommand command = CloseAccountCommand.builder()
                .accountId(id)
                .build();
        Account closed = closeAccountUseCase.close(command);
        return ResponseEntity.ok(accountMapper.toResponse(closed));
    }
}