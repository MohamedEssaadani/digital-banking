package com.essaadani.customerservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerResponseDTO {
    private Long id;
    private String name;
    private Date birthDate;
    private String cin;
    private String address;
    private String phoneNumber;
}
