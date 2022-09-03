package com.catering.app.controller.mapper;

import com.catering.app.model.api.response.OrderResponse;
import com.catering.app.model.domain.Order;

public class OrderApiMapper {
    public static OrderResponse mapToOrderResponse(Order order) {
        OrderResponse.CustomerDto customerDto = OrderApiMapper.mapToOrderCustomerResponse(order.getCustomer());

        return new OrderResponse(
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

    private static OrderResponse.CustomerDto mapToOrderCustomerResponse(Order.Customer customer) {
        return new OrderResponse.CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getCompanyName()
        );
    }
}
