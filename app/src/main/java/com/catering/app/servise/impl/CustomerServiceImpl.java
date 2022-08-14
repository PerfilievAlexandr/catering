package com.catering.app.servise.impl;

import com.catering.app.domain.models.customer.Customer;
import com.catering.app.repository.CustomerRepository;
import com.catering.app.servise.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAllCustomers();
    }
}
