package com.essaadani.account_service.dto;

import com.essaadani.account_service.enums.AccountStatus;
import com.essaadani.account_service.enums.AccountType;
import com.essaadani.account_service.models.Customer;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountResponseDTO {
    private Long id;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private BigDecimal balance;
    private Customer customer;
}
