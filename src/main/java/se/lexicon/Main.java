package se.lexicon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import se.lexicon.entity.Address;
import se.lexicon.entity.Customer;
import se.lexicon.entity.UserProfile;
import se.lexicon.repository.AddressRepository;
import se.lexicon.repository.CustomerRepository;
import se.lexicon.repository.UserProfileRepository;

import java.time.Instant;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner testData(
            CustomerRepository customerRepository,
            AddressRepository addressRepository,
            UserProfileRepository userProfileRepository
    ) {
        return args -> {

            System.out.println("=== Running Part 1 Repository Tests ===");

            // Create Address
            Address address = new Address();
            address.setStreet("Kanelbullevägen 7");
            address.setCity("Göteborg");
            address.setZipCode("41318");

            // Create UserProfile
            UserProfile profile = new UserProfile();
            profile.setNickname("fika.exe");
            profile.setPhoneNumber("0703338899");
            profile.setBio("Currently in a very important kanelbulle meeting.");

            // Create Customer
            Customer customer = new Customer();
            customer.setFirstName("Freja");
            customer.setLastName("Vanilj");
            customer.setEmail("freja.vanilj@fikasoft.se");
            customer.setAddress(address);
            customer.setProfile(profile);

            // Save Customer (cascades to Address + UserProfile)
           if (!customerRepository.existsByEmail(customer.getEmail())) {
               customerRepository.save(customer);
               System.out.println("Saved customer with ID: " + customer.getId());
           } else {
               System.out.println("Customer already exists.");
           }

            // test queries
            System.out.println("\n=== Query Tests === ");

            System.out.println("Find by email:");
            System.out.println(customerRepository.findByEmail("freja.vanilj@fikasoft.se"));

            System.out.println("\nFind by last name (ignore case):");
            System.out.println(customerRepository.findByLastNameIgnoreCase("vanilj"));

            System.out.println("\nFind by city (via Address)");
            System.out.println(customerRepository.findByAddress_CityIgnoreCase("göteborg"));

            System.out.println("\nFind by email containing 'fika': ");
            System.out.println(customerRepository.findByEmailContainingIgnoreCase("fika"));

            System.out.println("\nCount customers in Göteborg:");
            System.out.println(customerRepository.countByAddress_CityIgnoreCase("göteborg"));

            System.out.println("\nExists by Email:");
            System.out.println(customerRepository.existsByEmail("freja.vanilj@fikasoft.se"));

            System.out.println("\n=== Part 1 Test Completed ===");
            System.out.println("\n Have a nice day! ");


        };
    }




}
