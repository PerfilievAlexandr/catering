package com.catering.app.servise.impl;

import com.catering.app.exception.ResourceNotFoundException;
import com.catering.app.model.domain.Customer;
import com.catering.app.model.dto.CreateCustomerDto;
import com.catering.app.model.entity.CustomerEntity;
import com.catering.app.repository.CustomerRepository;
import com.catering.app.servise.CustomerService;
import com.catering.app.servise.mapper.CustomerEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerEntityMapper::mapToCustomer)
                .toList();
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Заказчик с id = %s не найден", customerId)));


        return CustomerEntityMapper.mapToCustomer(customerEntity);
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException(String.format("Заказчик с id = %s не найден", customerId) );
        }

        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer createCustomer(CreateCustomerDto createCustomerDto) {
        CustomerEntity customerEntity = customerRepository.save(CustomerEntityMapper.mapToCreateCustomerEntity(createCustomerDto));

        return CustomerEntityMapper.mapToCustomer(customerEntity);
    }
}
