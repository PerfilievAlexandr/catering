package com.catering.app.model.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateCustomerRequest {
    @NotBlank(message = "FirstName is required")
    private String firstName;

    @NotBlank(message = "LastName is required")
    private String lastName;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^\\d{10}$", message = "Wrong format")
    private String phone;

    @Email
    @NotBlank(message = "Email is required")
    private String email;

    private String companyName;
}
