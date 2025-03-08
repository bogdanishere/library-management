package com.example.spring_boot.configur;

import com.example.spring_boot.model.User;
import com.example.spring_boot.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;


@Configuration
public class AdminInitializer {

    @Bean
    public CommandLineRunner initializeAdmin(UserRepository userRepository) {
        return args -> {
            if(!userRepository.existAdmin()){
                User admin = new User();
                admin.setName("admin");
                admin.setPassword("admin");
                admin.setAdmin(true);
                userRepository.save(admin);

                System.out.println("Admin user created!");
            }else{
                System.out.println("Admin user already exists!");
            }
        };
    }
}
