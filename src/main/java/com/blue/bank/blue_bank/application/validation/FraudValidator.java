package com.blue.bank.blue_bank.application.validation;
import com.blue.bank.blue_bank.application.port.out.FraudCheckPort;
import com.blue.bank.blue_bank.domain.exception.FraudCheckException;
import com.blue.bank.blue_bank.domain.model.shared.FraudCheckResult;
import com.blue.bank.blue_bank.domain.model.transaction.TransferContext;
import com.blue.bank.blue_bank.domain.validation.TransferValidator;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class FraudValidator implements TransferValidator {
    private final FraudCheckPort fraudCheckPort;
    @Override
    public void validate(TransferContext ctx) {
        FraudCheckResult fraudCheckResult = fraudCheckPort.check(ctx.from().getId(), ctx.amount());
        if (fraudCheckResult.blocked()){
            throw new FraudCheckException(fraudCheckResult.reason());
        }
    }
}