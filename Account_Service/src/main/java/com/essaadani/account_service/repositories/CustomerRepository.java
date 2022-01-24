package com.essaadani.account_service.repositories;

import com.essaadani.account_service.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
