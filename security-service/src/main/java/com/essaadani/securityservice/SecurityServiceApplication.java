package com.essaadani.securityservice;

import com.essaadani.securityservice.sec.entities.AppRole;
import com.essaadani.securityservice.sec.entities.AppUser;
import com.essaadani.securityservice.sec.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityServiceApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner start(UserService userService){
        return args -> {
            userService.addNewUser(new AppUser(null, "mohamed", "123456", new ArrayList<>()));
            userService.addNewUser(new AppUser(null, "hicham", "123456",  new ArrayList<>()));
            userService.addNewUser(new AppUser(null, "saloi", "123456",  new ArrayList<>()));
            userService.addNewUser(new AppUser(null, "halima", "123456",  new ArrayList<>()));

            userService.addNewRole(new AppRole(null, "USER"));
            userService.addNewRole(new AppRole(null, "ADMIN"));
            userService.addNewRole(new AppRole(null, "CUSTOMER_MANAGER"));
            userService.addNewRole(new AppRole(null, "ACCOUNT_MANAGER"));
            userService.addNewRole(new AppRole(null, "OPERATION_MANAGER"));

            userService.addRoleToUser("mohamed", "ADMIN");
            userService.addRoleToUser("mohamed", "USER");
            userService.addRoleToUser("hicham", "CUSTOMER_MANAGER");
            userService.addRoleToUser("hicham", "USER");
            userService.addRoleToUser("saloi", "ACCOUNT_MANAGER");
            userService.addRoleToUser("saloi", "USER");
            userService.addRoleToUser("halima", "OPERATION_MANAGER");
            userService.addRoleToUser("halima", "USER");
        };
    }
}
