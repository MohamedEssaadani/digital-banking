package com.essaadani.account_service.service;

import com.essaadani.account_service.dto.AccountRequestDTO;
import com.essaadani.account_service.dto.AccountResponseDTO;

import java.util.List;

public interface AccountService {
    List<AccountResponseDTO> accountsList();
    AccountResponseDTO getAccountById(Long id);
    List<AccountResponseDTO> getAccountsByCustomerId(Long customerId);
    AccountResponseDTO saveAccount(AccountRequestDTO accountRequestDTO);
    void deleteAccountById(Long id);
    AccountResponseDTO updateAccount(Long id, AccountRequestDTO accountRequestDTO );
}
