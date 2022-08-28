package com.catering.app.repository.mapper;

import com.catering.app.domain.models.Customer;
import com.catering.app.repository.entity.CustomerEntity;
import com.catering.app.repository.entity.OrderEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CustomerMapper {
    public static Customer mapToCustomerDomain(CustomerEntity customerEntity) {
        Optional<List<OrderEntity>> optionalOrders = Optional.ofNullable(customerEntity.getOrderEntities());
        List<Customer.Order> orderList = optionalOrders
                .orElse(Collections.emptyList())
                .stream()
                .map(CustomerMapper::mapToOrderOfCustomer)
                .toList();

        return new Customer(
            customerEntity.getId(),
            customerEntity.getFirstName(),
            customerEntity.getLastName(),
            customerEntity.getPhone(),
            customerEntity.getEmail(),
            customerEntity.getCompanyName(),
                orderList
        );
    }

    private static Customer.Order mapToOrderOfCustomer(OrderEntity orderEntity) {
        return new Customer.Order(
                orderEntity.getId(),
                orderEntity.getNumber(),
                orderEntity.getStatus(),
                orderEntity.getEventDate(),
                orderEntity.getEventReason(),
                orderEntity.getPersonsQuantity(),
                orderEntity.getEventType(),
                orderEntity.getAddress(),
                orderEntity.getComment()
        );
    }
}
