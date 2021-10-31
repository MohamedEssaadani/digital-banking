package com.essaadani.operationservice.openfeign;

import com.essaadani.operationservice.models.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("ACCOUNT-SERVICE")
public interface AccountRestClient {
    @GetMapping("/api/accounts/{id}")
    Account getAccountById(@PathVariable Long id);
}
