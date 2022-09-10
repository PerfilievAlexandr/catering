package com.catering.app.servise.mapper;

import com.catering.app.model.domain.Customer;
import com.catering.app.model.dto.CreateCustomerDto;
import com.catering.app.model.entity.CustomerEntity;
import com.catering.app.model.entity.OrderEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CustomerEntityMapper {
    public static Customer mapToCustomer(CustomerEntity customerEntity) {
        Optional<List<OrderEntity>> optionalOrders = Optional.ofNullable(customerEntity.getOrderEntities());
        List<Customer.Order> orderList = optionalOrders
                .orElse(Collections.emptyList())
                .stream()
                .map(CustomerEntityMapper::mapToOrderOfCustomer)
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

    public static CustomerEntity mapToCreateCustomerEntity(CreateCustomerDto createCustomerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstName(createCustomerDto.getFirstName());
        customerEntity.setLastName(createCustomerDto.getLastName());
        customerEntity.setPhone(createCustomerDto.getPhone());
        customerEntity.setEmail(createCustomerDto.getEmail());
        customerEntity.setCompanyName(createCustomerDto.getCompanyName());

        return customerEntity;
    }
}
