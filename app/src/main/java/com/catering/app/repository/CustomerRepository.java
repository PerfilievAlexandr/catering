package com.catering.app.repository;

import com.catering.app.domain.models.customer.Customer;
import com.catering.app.repository.entity.CustomerEntity;
import com.catering.app.repository.mapper.CustomerMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    default List<Customer> findAllCustomers() {
        return findAll()
                .stream()
                .map(CustomerMapper::mapToCustomerDomain)
                .toList();
    }
}
