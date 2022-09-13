package com.catering.app.servise;

import com.catering.app.model.domain.Order;
import com.catering.app.model.dto.CreateOrderDto;
import com.catering.app.model.dto.UpdateOrderDto;

import java.util.List;

public interface OrderService {
    List<Order> getAll();

    Order getOrderById(Integer orderId);

    void deleteOrderById(Integer orderId);

    Order createOrder(CreateOrderDto createCustomerDto);

    Order updateOrder(UpdateOrderDto updateOrderDto);
}
