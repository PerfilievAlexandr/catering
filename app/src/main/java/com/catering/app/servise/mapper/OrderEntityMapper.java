package com.catering.app.servise.mapper;

import com.catering.app.model.domain.Order;
import com.catering.app.model.dto.CreateOrderDto;
import com.catering.app.model.dto.UpdateOrderDto;
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

    public static OrderEntity mapToOrderEntity(CreateOrderDto createOrderDto, CustomerEntity customerEntity) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setStatus(createOrderDto.getStatus());
        orderEntity.setEventDate(createOrderDto.getEventDate());
        orderEntity.setEventReason(createOrderDto.getEventReason());
        orderEntity.setPersonsQuantity(createOrderDto.getPersonsQuantity());
        orderEntity.setEventType(createOrderDto.getEventType());
        orderEntity.setAddress(createOrderDto.getAddress());
        orderEntity.setComment(createOrderDto.getComment());
        orderEntity.setCustomer(customerEntity);

        return orderEntity;
    }

    public static OrderEntity mapToUpdateOrderEntity(UpdateOrderDto updateOrderDto, CustomerEntity customerEntity) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setId(updateOrderDto.getId());
        orderEntity.setStatus(updateOrderDto.getStatus());
        orderEntity.setEventDate(updateOrderDto.getEventDate());
        orderEntity.setEventReason(updateOrderDto.getEventReason());
        orderEntity.setPersonsQuantity(updateOrderDto.getPersonsQuantity());
        orderEntity.setEventType(updateOrderDto.getEventType());
        orderEntity.setAddress(updateOrderDto.getAddress());
        orderEntity.setComment(updateOrderDto.getComment());
        orderEntity.setCustomer(customerEntity);

        return orderEntity;
    }
}
