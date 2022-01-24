package com.essaadani.customerservice.web;

import com.essaadani.customerservice.dto.CustomerRequestDTO;
import com.essaadani.customerservice.dto.CustomerResponseDTO;
import com.essaadani.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyWindowStore;
import org.apache.kafka.streams.state.WindowStoreIterator;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
//@CrossOrigin
public class CustomerRestAPI {
    private final CustomerService customerService;

    // to query kafka store
    private final InteractiveQueryService interactiveQueryService;

    @GetMapping("/customers")
    public List<CustomerResponseDTO> getCustomersList(){
        return customerService.customersList();
    }

    @GetMapping("/customers/{id}")
    public CustomerResponseDTO getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @GetMapping("/customers/byCin/{cin}")
    public CustomerResponseDTO getCustomerByCin(@PathVariable String cin){
        return customerService.getCustomerByCin(cin);
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
