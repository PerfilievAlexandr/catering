package com.catering.app.servise.impl;

import com.catering.app.exception.ResourceNotFoundException;
import com.catering.app.model.domain.Customer;
import com.catering.app.model.dto.CreateCustomerDto;
import com.catering.app.model.dto.UpdateCustomerDto;
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
        CustomerEntity customerEntity = getCustomerEntityById(customerId);


        return CustomerEntityMapper.mapToCustomer(customerEntity);
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException(String.format("Customer with id = %s not found", customerId) );
        }

        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer createCustomer(CreateCustomerDto createCustomerDto) {
        CustomerEntity customerEntity = customerRepository.save(CustomerEntityMapper.mapToCreateCustomerEntity(createCustomerDto));

        return CustomerEntityMapper.mapToCustomer(customerEntity);
    }

    @Override
    public Customer updateCustomer(UpdateCustomerDto updateCustomerDto) {
        CustomerEntity customerEntity = getCustomerEntityById(updateCustomerDto.getId());
        customerEntity.setFirstName(updateCustomerDto.getFirstName());
        customerEntity.setLastName(updateCustomerDto.getLastName());
        customerEntity.setPhone(updateCustomerDto.getPhone());
        customerEntity.setEmail(updateCustomerDto.getEmail());
        customerEntity.setCompanyName(updateCustomerDto.getCompanyName());

        return CustomerEntityMapper.mapToCustomer(customerRepository.save(customerEntity));
    }

    private CustomerEntity getCustomerEntityById(Integer customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Customer with id = %s not found", customerId)));
    }
}
