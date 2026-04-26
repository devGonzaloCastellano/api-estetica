package com.estetica.api_estetica.config;

import com.estetica.api_estetica.model.entity.Role;
import com.estetica.api_estetica.model.entity.User;
import com.estetica.api_estetica.repository.RoleRepository;
import com.estetica.api_estetica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Crea roles si no existen
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(null, "ADMIN", new HashSet<>()));
            roleRepository.save(new Role(null, "EMPLOYEE", new HashSet<>()));
            roleRepository.save(new Role(null, "CLIENT", new HashSet<>()));
        }

        // Crea admin si no existe
        if (userRepository.findUserEntityByUsername("admin").isEmpty()) {
            Role adminRole = roleRepository.findByName("ADMIN").get();
            User admin = User.builder()
                    .firstname("Admin")
                    .lastname("Sistema")
                    .email("admin@estetica.com")
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .enabled(true)
                    .accountNotLocked(true)
                    .roles(new HashSet<>(Set.of(adminRole)))
                    .build();
            userRepository.save(admin);
        }
    }
}
