package com.catering.app.repository;

import com.catering.app.exception.ResourceNotFoundException;
import com.catering.app.model.domain.Customer;
import com.catering.app.model.dto.CreateCustomerDto;
import com.catering.app.model.entity.CustomerEntity;
import com.catering.app.repository.mapper.CustomerEntityMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    default List<Customer> findAllCustomers() {
        return findAll()
                .stream()
                .map(CustomerEntityMapper::mapToCustomer)
                .toList();
    }

    default Customer findCustomerById(Integer customerId) {
        CustomerEntity customerEntity = findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Заказчик с id = %s не найден", customerId)));


        return CustomerEntityMapper.mapToCustomer(customerEntity);
    }

    default void deleteCustomerById(Integer customerId) {
        if (!existsById(customerId)) {
            throw new ResourceNotFoundException(String.format("Заказчик с id = %s не найден", customerId) );
        }

        deleteById(customerId);
    }

    default Customer createCustomer(CreateCustomerDto createCustomerDto) {
        CustomerEntity customerEntity = save(CustomerEntityMapper.mapToCreateCustomerEntity(createCustomerDto));

        return CustomerEntityMapper.mapToCustomer(customerEntity);
    }
}
