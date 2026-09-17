package com.blue.bank.blue_bank.infrastructure.adapter.in.rest.dto;
import com.blue.bank.blue_bank.domain.model.transaction.Transaction;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionResponse toResponse (Transaction transaction);
}