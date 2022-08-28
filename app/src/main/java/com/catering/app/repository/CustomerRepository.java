package com.catering.app.repository;

import com.catering.app.domain.models.Customer;
import com.catering.app.exception.ServiceException;
import com.catering.app.exception.errors.ApiErrorType;
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

    default Customer findCustomerById(Integer customerId) throws ServiceException {
        CustomerEntity customerEntity = findById(customerId)
                .orElseThrow(() -> new ServiceException(String.format("Заказчик с id = %s не найден", customerId), 404, ApiErrorType.NOT_FOUND ));


        return CustomerMapper.mapToCustomerDomain(customerEntity);
    }

    default void deleteCustomerById(Integer customerId) throws ServiceException {
        if (!existsById(customerId)) {
            throw new ServiceException(String.format("Заказчик с id = %s не найден", customerId), 404, ApiErrorType.NOT_FOUND );
        }

        deleteById(customerId);
    }
}
