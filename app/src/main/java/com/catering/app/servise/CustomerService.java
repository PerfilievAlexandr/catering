package com.catering.app.servise;

import com.catering.app.domain.models.customer.Customer;
import com.catering.app.exception.ServiceException;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();

    Customer getCustomerById(Integer customerId) throws ServiceException;

    void deleteCustomerById(Integer customerId) throws ServiceException;
}
