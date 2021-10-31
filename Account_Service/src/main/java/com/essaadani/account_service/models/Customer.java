package com.essaadani.account_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String name;
    private String cin;
    private String address;
    private String phoneNumber;
}
