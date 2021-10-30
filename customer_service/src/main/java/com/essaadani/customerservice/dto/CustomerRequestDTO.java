package com.essaadani.customerservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerRequestDTO {
    private String name;
    private Date birthDate;
    private String cin;
    private String address;
    private String phoneNumber;
}
