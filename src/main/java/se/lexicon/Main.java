package se.lexicon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import se.lexicon.entity.Address;
import se.lexicon.entity.Category;
import se.lexicon.entity.Customer;
import se.lexicon.entity.UserProfile;
import se.lexicon.repository.AddressRepository;
import se.lexicon.repository.CategoryRepository;
import se.lexicon.repository.CustomerRepository;
import se.lexicon.repository.UserProfileRepository;

import java.time.Instant;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }



    }








