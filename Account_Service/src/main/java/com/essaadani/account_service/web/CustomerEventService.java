package com.essaadani.account_service.web;

import com.essaadani.account_service.models.Customer;
import com.essaadani.account_service.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerEventService {
    private final CustomerRepository customerRepository;

    @Bean
    public Consumer<Customer> customerConsumer(){
        return (input)->{
            log.info("**********CONSUMING CUSTOMER TOPIC*************");
            log.info(input.getName());
            customerRepository.save(input);
        };
    }
}
