package com.catering.app.controller.mapper;

import com.catering.app.controller.dto.response.OrderDto;
import com.catering.app.domain.models.Order;

public class OrderDtoMapper {
    public static OrderDto mapToOrderDto(Order order) {
        OrderDto.CustomerDto customerDto = OrderDtoMapper.mapToCustomerDto(order.getCustomer());

        return new OrderDto(
                order.getId(),
                order.getNumber(),
                order.getStatus(),
                order.getEventDate(),
                order.getEventReason(),
                order.getPersonsQuantity(),
                order.getEventType(),
                order.getAddress(),
                order.getComment(),
                customerDto
        );
    }

    private static OrderDto.CustomerDto mapToCustomerDto(Order.Customer customer) {
        return new OrderDto.CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getCompanyName()
        );
    }
}
