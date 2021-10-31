package com.essaadani.customerservice.web;

import com.essaadani.customerservice.dto.CustomerRequestDTO;
import com.essaadani.customerservice.dto.CustomerResponseDTO;
import com.essaadani.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CustomerRestAPI {
    private final CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerResponseDTO> getCustomersList(){
        return customerService.customersList();
    }

    @GetMapping("/customers/{id}")
    public CustomerResponseDTO getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @PostMapping("/customers")
    public CustomerResponseDTO saveCustomer(@RequestBody CustomerRequestDTO customerRequestDTO){
        return customerService.saveCustomer(customerRequestDTO);
    }

    @PutMapping("/customers/{id}")
    public CustomerResponseDTO updateCustomer(@PathVariable Long id,
                                              @RequestBody CustomerRequestDTO customerRequestDTO)
    {
        return customerService.updateCustomer(id, customerRequestDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);
    }

}
