package com.catering.app.controller.mapper;

import com.catering.app.controller.dto.request.CreateCustomerDto;
import com.catering.app.controller.dto.response.CustomerDto;
import com.catering.app.domain.models.Customer;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CustomerDtoMapper {
    public static CustomerDto mapToCustomerDto(Customer customer) {
        Optional<List<Customer.Order>> optionalOrders = Optional.ofNullable(customer.getOrders());
        List<CustomerDto.OrderDto> orderList = optionalOrders
                .orElse(Collections.emptyList())
                .stream()
                .map(CustomerDtoMapper::mapToCustomerDto)
                .toList();

        return new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getCompanyName(),
                orderList
        );
    }

    private static CustomerDto.OrderDto mapToCustomerDto(Customer.Order orderOfCustomer) {
        return new CustomerDto.OrderDto(
                orderOfCustomer.getId(),
                orderOfCustomer.getNumber(),
                orderOfCustomer.getStatus(),
                orderOfCustomer.getEventDate(),
                orderOfCustomer.getEventReason(),
                orderOfCustomer.getPersonsQuantity(),
                orderOfCustomer.getEventType(),
                orderOfCustomer.getAddress(),
                orderOfCustomer.getComment()
        );
    }

    public static Customer mapToCreateCustomer(CreateCustomerDto createCustomerDto) {
        Customer customer = new Customer();
        customer.setFirstName(createCustomerDto.getFirstName());
        customer.setLastName(createCustomerDto.getLastName());
        customer.setPhone(createCustomerDto.getPhone());
        customer.setEmail(createCustomerDto.getEmail());
        customer.setCompanyName(createCustomerDto.getCompanyName());

        return customer;
    }
}
