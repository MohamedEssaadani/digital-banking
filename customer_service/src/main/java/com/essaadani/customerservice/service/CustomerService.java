package com.essaadani.customerservice.service;

import com.essaadani.customerservice.dto.CustomerRequestDTO;
import com.essaadani.customerservice.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseDTO> customersList();
    CustomerResponseDTO getCustomerById(Long id);
    CustomerResponseDTO getCustomerByCin(String cin);
    CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO);
    void deleteCustomerById(Long id);
    CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO customerRequestDTO);

}
