package com.catering.app.domain.models.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOfOrder {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String company_name;
}
