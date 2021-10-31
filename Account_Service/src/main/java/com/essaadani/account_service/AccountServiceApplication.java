package com.essaadani.account_service;

import com.essaadani.account_service.entities.Account;
import com.essaadani.account_service.enums.AccountStatus;
import com.essaadani.account_service.enums.AccountType;
import com.essaadani.account_service.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountRepository accountRepository){
        return args -> {
          accountRepository.save(new Account(null, AccountType.COURANT, AccountStatus.CREATED, BigDecimal.valueOf(Math.random() * 100000), 1L, null ));
          accountRepository.save(new Account(null, AccountType.COURANT, AccountStatus.CREATED, BigDecimal.valueOf(Math.random() * 100000), 2L, null ));
          accountRepository.save(new Account(null, AccountType.COURANT, AccountStatus.CREATED, BigDecimal.valueOf(Math.random() * 100000), 3L, null ));
        };
    }
}
