package com.essaadani.customerservice.mappers;

import com.essaadani.customerservice.dto.CustomerRequestDTO;
import com.essaadani.customerservice.dto.CustomerResponseDTO;
import com.essaadani.customerservice.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponseDTO customerToCustomerDTO(Customer customer);
    Customer customerDTOtoCustomer(CustomerRequestDTO customerRequestDTO);
}
