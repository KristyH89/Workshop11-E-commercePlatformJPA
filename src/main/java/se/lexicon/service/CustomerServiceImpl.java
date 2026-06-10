package se.lexicon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.lexicon.dto.CustomerRequestDto;
import se.lexicon.dto.CustomerResponseDto;
import se.lexicon.entity.Address;
import se.lexicon.entity.Customer;
import se.lexicon.exception.EmailAlreadyExistsException;
import se.lexicon.exception.ResourceNotFoundException;
import se.lexicon.mapper.CustomerMapper;
import se.lexicon.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponseDto register(CustomerRequestDto request) {

        // Check if email is already taken
        if (customerRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(
                    "Email already exists: " + request.email()
            );
        }

        // Convert request to entity and save
        Customer customer = customerMapper.toEntity(request);
        Customer savedCustomer = customerRepository.save(customer);

        // Concert saved entity to response and return
        return customerMapper.toResponse(savedCustomer);
    }

    @Override
    public CustomerResponseDto findById(Long id) {

        // Fetch customer or throw if not found
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with id: " + id
                ));
        return customerMapper.toResponse(customer);
    }

    @Override
    public CustomerResponseDto update(Long id, CustomerRequestDto request) {

        // Fetch existing customer or throw if not found
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with Id: " + id
                ));

        // Update customer fields
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());

        // Update address fields
        Address address = customer.getAddress();
        if (address == null) {
            address = new Address();
        }
        address.setStreet(request.street());
        address.setCity(request.city());
        address.setZipCode(request.zipCode());
        customer.setAddress(address);

        // save and return updated customer
        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.toResponse(updatedCustomer);
    }
}
