package com.essaadani.customerservice.service;

import com.essaadani.customerservice.dto.CustomerRequestDTO;
import com.essaadani.customerservice.dto.CustomerResponseDTO;
import com.essaadani.customerservice.entities.Customer;
import com.essaadani.customerservice.mappers.CustomerMapper;
import com.essaadani.customerservice.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerResponseDTO> customersList() {
        // get customers list
        List<Customer> customers = customerRepository.findAll();

        // convert customers to customers dto and return them
        return customers
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        // get customer by id, then convert it into customer dto and return the result
        return customerMapper.customerToCustomerDTO(
                customerRepository.findById(id).get()
        );
    }

    @Override
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO) {
        // convert customer dto to customer
        Customer customer = customerMapper.customerDTOtoCustomer(customerRequestDTO);
        // save customer
        Customer savedCustomer = customerRepository.save(customer);

        // convert customer to customer dto and return the saved customer
        return customerMapper.customerToCustomerDTO(savedCustomer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        // (we should check if he don't have accounts first before delete)
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.customerDTOtoCustomer(customerRequestDTO);

        // set the id in the param to the customer
        customer.setId(id);
        // update customer
        Customer updatedCustomer = customerRepository.save(customer);

        // convert customer to customer dto and return the updated customer
        return customerMapper.customerToCustomerDTO(updatedCustomer);
    }


}
