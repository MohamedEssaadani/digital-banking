package com.essaadani.customerservice;

import com.essaadani.customerservice.entities.Customer;
import com.essaadani.customerservice.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


import java.util.Date;

@SpringBootApplication
@Slf4j
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository){
        return args -> {
          customerRepository.save(new Customer(null, "ESSAADANI Mohamed", new Date("18/01/1998"), "AE177349", "Casablanca", "0694496795"));
          customerRepository.save(new Customer(null, "OUAHBI Youssef", new Date("18/01/1991"), "SS8733", "El Jadida", "06774488"));
          customerRepository.save(new Customer(null, "ESSAADANI Hicham", new Date("12/05/1985"), "MM9933", "Sale", "067733992"));

          customerRepository.findAll()
                  .forEach(customer -> {
                      log.info("Customer : {}", customer.getName());
                  });
        };


    }


}
