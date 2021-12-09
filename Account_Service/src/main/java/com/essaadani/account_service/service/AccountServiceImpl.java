package com.essaadani.account_service.service;

import com.essaadani.account_service.dto.AccountRequestDTO;
import com.essaadani.account_service.dto.AccountResponseDTO;
import com.essaadani.account_service.entities.Account;
import com.essaadani.account_service.enums.AccountStatus;
import com.essaadani.account_service.exceptions.CustomerNotFoundException;
import com.essaadani.account_service.mappers.AccountMapper;
import com.essaadani.account_service.models.Customer;
import com.essaadani.account_service.openfeign.CustomerRestClient;
import com.essaadani.account_service.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final CustomerRestClient customerRestClient;

    @Override
    public List<AccountResponseDTO> accountsList(String token) {
        List<Account> accounts = accountRepository.findAll();
        accounts.forEach(account -> {
            account.setCustomer(customerRestClient.getCustomerById(account.getCustomerId(), token));
        });
        return accounts
                .stream()
                .map(accountMapper::toAccountDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id).get();
       // account.setCustomer(customerRestClient.getCustomerById(account.getCustomerId()));
        return accountMapper.toAccountDTO(account);
    }
    @Override
    public List<AccountResponseDTO> getAccountsByCustomerId(Long customerId, String token) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        for (Account account : accounts) {
            account.setCustomer(customerRestClient.getCustomerById(customerId, token));
        }
        return accounts.stream()
                .map(accountMapper::toAccountDTO)
                .collect(Collectors.toList());
    }
    @Override
    public AccountResponseDTO saveAccount(AccountRequestDTO accountRequestDTO, String token) {
        // Verifier si le customer existe
        Customer customer;
        try{
            customer = customerRestClient.getCustomerById(accountRequestDTO.getCustomerId(), token);
        }catch (Exception e){
            throw new CustomerNotFoundException("Customer Not Found!");
        }

        Account account = accountMapper.toAccount(accountRequestDTO);
        account.setCustomer(customer);
        account.setAccountStatus(AccountStatus.CREATED);
        Account savedAccount = accountRepository.save(account);

        return accountMapper.toAccountDTO(savedAccount);
    }

    @Override
    public void deleteAccountById(Long id) {
        // set status to deactivated
        accountRepository.findById(id).get().setAccountStatus(AccountStatus.DEACTIVATED);
    }

    @Override
    public AccountResponseDTO updateAccount(Long id, AccountRequestDTO accountRequestDTO) {
        Account account = accountMapper.toAccount(accountRequestDTO);
        Account updatedAccount = accountRepository.save(account);
        return accountMapper.toAccountDTO(updatedAccount);
    }
}
