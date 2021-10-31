package com.essaadani.account_service.repositories;

import com.essaadani.account_service.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByCustomerId(Long customerId);
}
