package com.example.GestionePrenotazioni.runner;


import com.example.GestionePrenotazioni.model.User;
import com.example.GestionePrenotazioni.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create and save a new user
        User user = new User("john_doe", "John Doe", "john.doe@example.com");
        userRepository.save(user);

        // Print all users
        userRepository.findAll().forEach(System.out::println);
    }
}
