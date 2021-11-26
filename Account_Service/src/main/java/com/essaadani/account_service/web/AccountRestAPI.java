package com.essaadani.account_service.web;

import com.essaadani.account_service.dto.AccountRequestDTO;
import com.essaadani.account_service.dto.AccountResponseDTO;
import com.essaadani.account_service.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
//@CrossOrigin("*")
@RequiredArgsConstructor
public class AccountRestAPI {
    private final AccountService accountService;

    @GetMapping("/accounts")
    public List<AccountResponseDTO> accountsList(@RequestHeader HttpHeaders headers){
        return accountService.accountsList(headers.get("Authorization").get(0));
    }

    @GetMapping("/accounts/{id}")
    public AccountResponseDTO getAccount(@PathVariable Long id){
        return accountService.getAccountById(id);
    }

    @GetMapping("/accounts/byCustomer/{customerId}")
    public List<AccountResponseDTO> getAccountsByCustomer(@PathVariable Long customerId,
                                                          @RequestHeader HttpHeaders headers){
        return accountService.getAccountsByCustomerId(customerId, headers.get("Authorization").get(0));
    }

    @PostMapping("/accounts")
    public AccountResponseDTO saveAccount(@RequestBody AccountRequestDTO accountRequestDTO,
                                          @RequestHeader HttpHeaders headers){
        return accountService.saveAccount(accountRequestDTO, headers.get("Authorization").get(0));
    }

    @PutMapping("/accounts/{id}")
    public AccountResponseDTO updateAccount(@PathVariable Long id, @RequestBody AccountRequestDTO accountRequestDTO){
        return accountService.updateAccount(id, accountRequestDTO);
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable Long id){
        accountService.deleteAccountById(id);
    }

}
