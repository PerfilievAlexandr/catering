package com.catering.app.repository.mapper;

import com.catering.app.domain.models.Order;
import com.catering.app.repository.entity.CustomerEntity;
import com.catering.app.repository.entity.OrderEntity;

public class OrderMapper {
    public static Order mapToDomainOrder(OrderEntity orderEntity) {
        Order.Customer customerOfOrder = OrderMapper.mapToCustomerOfOrder(orderEntity.getCustomer());

        return new Order(
                orderEntity.getId(),
                orderEntity.getNumber(),
                orderEntity.getStatus(),
                orderEntity.getEventDate(),
                orderEntity.getEventReason(),
                orderEntity.getPersonsQuantity(),
                orderEntity.getEventType(),
                orderEntity.getAddress(),
                orderEntity.getComment(),
                customerOfOrder
        );
    }

    private static Order.Customer mapToCustomerOfOrder(CustomerEntity customerEntity) {
        return new Order.Customer(
                customerEntity.getId(),
                customerEntity.getFirstName(),
                customerEntity.getLastName(),
                customerEntity.getPhone(),
                customerEntity.getEmail(),
                customerEntity.getCompanyName()
        );
    }
}
