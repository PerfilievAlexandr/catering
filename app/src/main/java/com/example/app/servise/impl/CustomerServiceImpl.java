package com.example.app.servise.impl;

import com.example.app.repository.CustomerRepository;
import com.example.app.repository.entity.Customer;
import com.example.app.servise.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}
