package com.essaadani.account_service.entities;

import com.essaadani.account_service.enums.AccountStatus;
import com.essaadani.account_service.enums.AccountType;
import com.essaadani.account_service.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    private BigDecimal balance;
    private Long customerId;

    @Transient
    private Customer customer;
}
