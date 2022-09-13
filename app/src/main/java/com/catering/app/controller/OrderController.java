package com.catering.app.controller;

import com.catering.app.controller.mapper.OrderApiMapper;
import com.catering.app.model.api.request.CreateUpdateOrderRequest;
import com.catering.app.model.api.response.OrderResponse;
import com.catering.app.model.domain.Order;
import com.catering.app.servise.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping()
    @ApiOperation(value = "Получить список заказов")
    @ApiResponse(code = 200, message = "", response = OrderResponse.class)
    public List<OrderResponse> getAllOrders() {
        return orderService.getAll()
                .stream()
                .map(OrderApiMapper::mapToOrderResponse)
                .toList();
    }

    @GetMapping("/{orderId}")
    @ApiOperation(value = "Получить заказ по id")
    @ApiResponse(code = 200, message = "", response = OrderResponse.class)
    public OrderResponse getOrderById(@PathVariable @NotNull Integer orderId) {
        return OrderApiMapper.mapToOrderResponse(orderService.getOrderById(orderId));
    }

    @DeleteMapping("/{orderId}")
    @ApiOperation(value = "Удалить заказ по id")
    @ApiResponse(code = 200, message = "", response = OrderResponse.class)
    public void deleteOrderById(@PathVariable @NotNull Integer orderId) {
        orderService.deleteOrderById(orderId);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Создать заказ")
    @ApiResponse(code = 200, message = "", response = OrderResponse.class)
    public Order createOrder(@Valid @RequestBody CreateUpdateOrderRequest orderRequest) {
        return orderService.createOrder(OrderApiMapper.mapToCreateOrderDto(orderRequest));
    }

    @PutMapping("/{orderId}/update")
    @ApiOperation(value = "Обновить заказ")
    @ApiResponse(code = 200, message = "", response = OrderResponse.class)
    public Order updateOrder(@PathVariable @NotNull Integer orderId, @Valid @RequestBody CreateUpdateOrderRequest orderRequest) {
        return orderService.updateOrder(OrderApiMapper.mapToUpdateOrderDto(orderRequest, orderId));
    }
}
