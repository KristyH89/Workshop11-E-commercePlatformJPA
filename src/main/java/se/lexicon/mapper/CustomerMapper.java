package se.lexicon.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.dto.AddressResponseDto;
import se.lexicon.dto.CustomerRequestDto;
import se.lexicon.dto.CustomerResponseDto;
import se.lexicon.entity.Address;
import se.lexicon.entity.Customer;


@Component
public class CustomerMapper {

    // ENTITY -> RESPONSE
    public CustomerResponseDto toResponse(Customer customer) {
        if (customer == null) return null;

        return new CustomerResponseDto(
                customer.getId(),
                customer.getFirstName() + " " + customer.getLastName(),
                customer.getEmail(),
                toAddressResponse(customer.getAddress())
        );
    }

    // Helper method to map Address entity to AddressResponse
    private AddressResponseDto toAddressResponse(Address address) {
        if (address == null) return null;

        return new AddressResponseDto(
                address.getStreet(),
                address.getCity(),
                address.getZipCode()
        );
    }

    // REQUEST -> ENTITY
    public Customer toEntity(CustomerRequestDto request) {
        if (request == null) return null;

        // Build the Address using setters
        Address address = new Address();
        address.setStreet(request.street());
        address.setCity(request.city());
        address.setZipCode(request.zipCode());

       // Build the Customer using setters
        Customer customer = new Customer();
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setAddress(address);

        return customer;
    }

}
