package se.lexicon.service;

import se.lexicon.dto.CustomerRequestDto;
import se.lexicon.dto.CustomerResponseDto;
import se.lexicon.entity.Customer;
import se.lexicon.exception.EmailAlreadyExistsException;

import java.util.List;

public interface CustomerService {

    // Register a new customer
    CustomerResponseDto register(CustomerRequestDto request);

    // Find a customer by ID
    CustomerResponseDto findById(Long id);

    // Update an existing customer
    CustomerResponseDto update(Long id, CustomerRequestDto request);

}
