package com.essaadani.customerservice;

import com.essaadani.customerservice.entities.Customer;
import com.essaadani.customerservice.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@Slf4j
public class CustomerServiceApplication {
    @Autowired
    private StreamBridge streamBridge;

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository){
        return args -> {
          customerRepository.save(new Customer(null, "ESSAADANI Mohamed", new Date("18/01/1998"), "AE177349", "Casablanca", "0694496795", new Date()));
          customerRepository.save(new Customer(null, "OUAHBI Youssef", new Date("18/01/1991"), "SS8733", "El Jadida", "06774488", new Date()));
          customerRepository.save(new Customer(null, "ESSAADANI Hicham", new Date("12/05/1985"), "MM9933", "Sale", "067733992", new Date()));

          customerRepository.findAll()
                  .forEach(customer -> {
                      log.info("*****************Pushing to customers topic***************");
                      streamBridge.send("CUSTOMERS-TOPIC", customer);
                      log.info("Customer : {}", customer.getName());
                  });
        };
    }
}
