package com.catering.app.servise.impl;

import com.catering.app.model.domain.Customer;
import com.catering.app.exception.ServiceException;
import com.catering.app.model.dto.CreateCustomerDto;
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

    @Override
    public Customer getCustomerById(Integer customerId) throws ServiceException {
        return customerRepository.findCustomerById(customerId);
    }

    @Override
    public void deleteCustomerById(Integer customerId) throws ServiceException {
        customerRepository.deleteCustomerById(customerId);
    }

    @Override
    public Customer createCustomer(CreateCustomerDto createCustomerDto) {
        return customerRepository.createCustomer(createCustomerDto);
    }
}
