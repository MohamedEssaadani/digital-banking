package com.essaadani.operationservice.openfeign;

import com.essaadani.operationservice.models.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("ACCOUNT-SERVICE")
public interface AccountRestClient {
    @GetMapping("/api/accounts/{id}")
    Account getAccountById(@PathVariable Long id, @RequestHeader("Authorization") String access_token);

    @PutMapping("/accounts/{id}")
    Account updateAccount(@PathVariable Long id, @RequestBody Account account);
}
