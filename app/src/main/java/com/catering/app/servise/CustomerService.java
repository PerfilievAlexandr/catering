package com.catering.app.servise;

import com.catering.app.model.domain.Customer;
import com.catering.app.model.dto.CreateCustomerDto;
import com.catering.app.model.dto.UpdateCustomerDto;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();

    Customer getCustomerById(Integer customerId);

    void deleteCustomerById(Integer customerId);

    Customer createCustomer(CreateCustomerDto createCustomerDto);

    Customer updateCustomer(UpdateCustomerDto updateCustomerDto);
}
