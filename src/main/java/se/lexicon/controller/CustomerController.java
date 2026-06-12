package se.lexicon.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.dto.CustomerRequestDto;
import se.lexicon.dto.CustomerResponseDto;
import se.lexicon.service.CustomerService;

/**
 * REST controller for managing customers.
 * Exposes endpoints under /api/v1/customers.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Registers a new customer.
     *
     * @param request the customer data to register
     * @return the registered customer with HTTP 201 Created
     * @throws se.lexicon.exception.EmailAlreadyExistsException if the email is already in use
     */
    @PostMapping
    public ResponseEntity<CustomerResponseDto> register(
            @Valid @RequestBody CustomerRequestDto request) {
        CustomerResponseDto response = customerService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Retrieves a single customer by their ID.
     *
     * @param id the ID of the customer
     * @return the found customer with HTTP 200 OK
     * @throws se.lexicon.exception.ResourceNotFoundException if no customer exists with the given ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> findById(@PathVariable Long id) {
        CustomerResponseDto response = customerService.findById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates an existing customer.
     *
     * @param id the ID of the customer to update
     * @param request the updated customer data
     * @return the updates customer with HTTP 200 OK
     * @throws se.lexicon.exception.ResourceNotFoundException if no customer exists with the given ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDto request) {
        CustomerResponseDto updated = customerService.update(id, request);
        return ResponseEntity.ok(updated);
    }

}
