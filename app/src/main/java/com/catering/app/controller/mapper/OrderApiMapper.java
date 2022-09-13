package com.catering.app.controller.mapper;

import com.catering.app.model.api.request.CreateUpdateOrderRequest;
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

    public static CreateOrderDto mapToCreateOrderDto(CreateUpdateOrderRequest createUpdateOrderRequest) {
        return new CreateOrderDto(
                createUpdateOrderRequest.getStatus(),
                createUpdateOrderRequest.getEventDate(),
                createUpdateOrderRequest.getEventReason(),
                createUpdateOrderRequest.getPersonsQuantity(),
                createUpdateOrderRequest.getEventType(),
                createUpdateOrderRequest.getAddress(),
                createUpdateOrderRequest.getComment(),
                createUpdateOrderRequest.getCustomerId()
        );
    }

    public static UpdateOrderDto mapToUpdateOrderDto(CreateUpdateOrderRequest updateOrderRequest, int orderId) {
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
