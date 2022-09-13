package com.catering.app.controller.mapper;

import com.catering.app.model.api.request.CreateOrderRequest;
import com.catering.app.model.api.request.UpdateOrderRequest;
import com.catering.app.model.api.response.OrderResponse;
import com.catering.app.model.domain.Order;
import com.catering.app.model.dto.CreateOrderDto;
import com.catering.app.model.dto.UpdateOrderDto;

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

    public static CreateOrderDto mapToCreateOrderDto(CreateOrderRequest createOrderRequest) {
        return new CreateOrderDto(
                createOrderRequest.getStatus(),
                createOrderRequest.getEventDate(),
                createOrderRequest.getEventReason(),
                createOrderRequest.getPersonsQuantity(),
                createOrderRequest.getEventType(),
                createOrderRequest.getAddress(),
                createOrderRequest.getComment(),
                createOrderRequest.getCustomerId()
        );
    }

    public static UpdateOrderDto mapToUpdateOrderDto(UpdateOrderRequest updateOrderRequest, int orderId) {
        return new UpdateOrderDto(
                orderId,
                updateOrderRequest.getStatus(),
                updateOrderRequest.getEventDate(),
                updateOrderRequest.getEventReason(),
                updateOrderRequest.getPersonsQuantity(),
                updateOrderRequest.getEventType(),
                updateOrderRequest.getAddress(),
                updateOrderRequest.getComment(),
                updateOrderRequest.getCustomerId()
        );
    }
}
