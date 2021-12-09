package com.essaadani.customerservice.repositories;

import com.essaadani.customerservice.dto.CustomerResponseDTO;
import com.essaadani.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCin(String cin);
}
