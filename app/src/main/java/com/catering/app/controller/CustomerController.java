package com.catering.app.controller;

import com.catering.app.domain.models.customer.Customer;
import com.catering.app.servise.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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
    @ApiOperation(value = "Получить список сотрудников")
    @ApiResponse(code = 200, message = "", response = Customer.class)
    public List<Customer> getAllCustomers() {
        return customerService.getAll();
    }
}
