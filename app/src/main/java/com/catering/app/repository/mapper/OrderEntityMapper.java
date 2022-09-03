package com.catering.app.repository.mapper;

import com.catering.app.model.domain.Order;
import com.catering.app.model.entity.CustomerEntity;
import com.catering.app.model.entity.OrderEntity;

public class OrderEntityMapper {
    public static Order mapToOrder(OrderEntity orderEntity) {
        Order.Customer customerOfOrder = OrderEntityMapper.mapToCustomerOfOrder(orderEntity.getCustomer());

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
