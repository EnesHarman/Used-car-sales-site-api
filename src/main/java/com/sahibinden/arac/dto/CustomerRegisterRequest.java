package com.sahibinden.arac.dto;

import com.sahibinden.arac.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CustomerRegisterRequest {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private Address address;
}
