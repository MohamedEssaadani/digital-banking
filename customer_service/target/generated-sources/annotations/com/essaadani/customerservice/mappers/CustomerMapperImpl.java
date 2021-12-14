package com.essaadani.customerservice.mappers;

import com.essaadani.customerservice.dto.CustomerRequestDTO;
import com.essaadani.customerservice.dto.CustomerResponseDTO;
import com.essaadani.customerservice.entities.Customer;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-14T21:45:32+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerResponseDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();

        customerResponseDTO.setId( customer.getId() );
        customerResponseDTO.setName( customer.getName() );
        customerResponseDTO.setBirthDate( customer.getBirthDate() );
        customerResponseDTO.setCin( customer.getCin() );
        customerResponseDTO.setAddress( customer.getAddress() );
        customerResponseDTO.setPhoneNumber( customer.getPhoneNumber() );

        return customerResponseDTO;
    }

    @Override
    public Customer customerDTOtoCustomer(CustomerRequestDTO customerRequestDTO) {
        if ( customerRequestDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setName( customerRequestDTO.getName() );
        customer.setBirthDate( customerRequestDTO.getBirthDate() );
        customer.setCin( customerRequestDTO.getCin() );
        customer.setAddress( customerRequestDTO.getAddress() );
        customer.setPhoneNumber( customerRequestDTO.getPhoneNumber() );

        return customer;
    }
}
