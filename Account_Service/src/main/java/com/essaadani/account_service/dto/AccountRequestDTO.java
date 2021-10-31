package com.essaadani.account_service.dto;

import com.essaadani.account_service.enums.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountRequestDTO {
    private AccountType accountType;
    private BigDecimal balance;
    private Long customerId;
}
