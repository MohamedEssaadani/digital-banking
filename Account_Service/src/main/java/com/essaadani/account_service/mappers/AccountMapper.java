package com.essaadani.account_service.mappers;

import com.essaadani.account_service.dto.AccountRequestDTO;
import com.essaadani.account_service.dto.AccountResponseDTO;
import com.essaadani.account_service.entities.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountResponseDTO toAccountDTO(Account account);
    Account toAccount(AccountRequestDTO accountRequestDTO);
}
