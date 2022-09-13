package com.catering.app.servise.impl;

import com.catering.app.exception.ResourceNotFoundException;
import com.catering.app.model.domain.Order;
import com.catering.app.model.dto.CreateOrderDto;
import com.catering.app.model.dto.UpdateOrderDto;
import com.catering.app.model.entity.CustomerEntity;
import com.catering.app.model.entity.OrderEntity;
import com.catering.app.repository.CustomerRepository;
import com.catering.app.repository.OrderRepository;
import com.catering.app.servise.OrderService;
import com.catering.app.servise.mapper.OrderEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(OrderEntityMapper::mapToOrder)
                .toList();
    }

    @Override
    public Order getOrderById(Integer orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Order with id = %s not found", orderId)));


        return OrderEntityMapper.mapToOrder(orderEntity);
    }

    @Override
    public void deleteOrderById(Integer orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new ResourceNotFoundException(String.format("Order with id = %s not found", orderId) );
        }

        orderRepository.deleteById(orderId);
    }

    @Override
    public Order createOrder(CreateOrderDto createOrderDto) {
        CustomerEntity customerEntity = customerRepository.findById(createOrderDto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Customer with id = %s not found", createOrderDto.getCustomerId())));

        OrderEntity orderEntity = orderRepository.save(OrderEntityMapper.mapToOrderEntity(createOrderDto, customerEntity));

        return OrderEntityMapper.mapToOrder(orderEntity);
    }

    @Override
    public Order updateOrder(UpdateOrderDto updateOrderDto) {
        CustomerEntity customerEntity = customerRepository.findById(updateOrderDto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Customer with id = %s not found", updateOrderDto.getCustomerId())));

        OrderEntity orderEntity = orderRepository.save(OrderEntityMapper.mapToUpdateOrderEntity(updateOrderDto, customerEntity));

        return OrderEntityMapper.mapToOrder(orderEntity);
    }
}
