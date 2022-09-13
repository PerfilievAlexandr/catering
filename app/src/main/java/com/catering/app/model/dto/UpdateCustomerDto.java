package com.catering.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String companyName;
}
