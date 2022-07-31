package com.example.app.controller;

import com.example.app.repository.entity.Customer;
import com.example.app.servise.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public List<Customer> getAllCustomers() {
        return customerService.getAll();
    }
}
