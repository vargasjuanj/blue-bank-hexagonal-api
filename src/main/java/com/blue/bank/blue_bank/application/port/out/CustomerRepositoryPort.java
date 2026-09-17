package com.blue.bank.blue_bank.application.port.out;
import com.blue.bank.blue_bank.domain.model.customer.Customer;
import java.util.Optional;
public interface CustomerRepositoryPort {
    Optional<Customer> findById(Long id);
    Customer save(Customer customer);
}