package com.catering.app.servise;

import com.catering.app.domain.models.customer.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();
}
