package com.essaadani.operationservice;

import com.essaadani.operationservice.entities.Operation;
import com.essaadani.operationservice.enums.OperationType;
import com.essaadani.operationservice.repositories.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
@EnableFeignClients
@Slf4j
public class OperationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperationServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(OperationRepository operationRepository){
        return args -> {
            operationRepository.save(
                    new Operation(null, new Date(), BigDecimal.valueOf(100), OperationType.CREDIT, 1L, null)
            );
            operationRepository.save(
                    new Operation(null, new Date(), BigDecimal.valueOf(50), OperationType.DEBIT, 1L, null)
            );
            operationRepository.save(
                    new Operation(null, new Date(), BigDecimal.valueOf(900), OperationType.CREDIT, 2L, null)
            );
            operationRepository.save(
                    new Operation(null, new Date(), BigDecimal.valueOf(250), OperationType.DEBIT, 2L, null)
            );

            operationRepository.findAll()
                    .forEach(operation -> {
                       log.info("Operation Date: {}, Operation Amount: {}, Operation Type: {}", operation.getOperationDate(), operation.getAmount(), operation.getOperationType());
                    });
        };
    }
}
