package com.essaadani.account_service.service;

import com.essaadani.account_service.dto.AccountRequestDTO;
import com.essaadani.account_service.dto.AccountResponseDTO;

import java.util.List;

public interface AccountService {
    List<AccountResponseDTO> accountsList(String token);
    AccountResponseDTO getAccountById(Long id);
    List<AccountResponseDTO> getAccountsByCustomerId(Long customerId, String token);
    AccountResponseDTO saveAccount(AccountRequestDTO accountRequestDTO, String token);
    void deleteAccountById(Long id);
    AccountResponseDTO updateAccount(Long id, AccountRequestDTO accountRequestDTO );
}
