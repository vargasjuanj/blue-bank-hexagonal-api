package com.blue.bank.blue_bank.application.command;
import lombok.Builder;
@Builder
public record CloseAccountCommand(
        Long accountId
) {
}