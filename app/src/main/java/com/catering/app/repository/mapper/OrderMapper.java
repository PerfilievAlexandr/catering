package com.catering.app.repository.mapper;

import com.catering.app.domain.models.order.CustomerOfOrder;
import com.catering.app.domain.models.order.Order;
import com.catering.app.repository.entity.CustomerEntity;
import com.catering.app.repository.entity.OrderEntity;

public class OrderMapper {
    public static Order mapToDomainOrder(OrderEntity orderEntity) {
        CustomerOfOrder customerOfOrder = OrderMapper.mapToCustomerOfOrder(orderEntity.getCustomer());

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

    private static CustomerOfOrder mapToCustomerOfOrder(CustomerEntity customerEntity) {
        return new CustomerOfOrder(
                customerEntity.getId(),
                customerEntity.getFirstName(),
                customerEntity.getLastName(),
                customerEntity.getPhone(),
                customerEntity.getEmail(),
                customerEntity.getCompanyName()
        );
    }
}
