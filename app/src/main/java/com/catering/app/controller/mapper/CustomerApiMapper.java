package com.catering.app.controller.mapper;

import com.catering.app.model.api.request.CreateCustomerRequest;
import com.catering.app.model.api.response.CustomerResponse;
import com.catering.app.model.domain.Customer;
import com.catering.app.model.dto.CreateCustomerDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CustomerApiMapper {
    public static CustomerResponse mapToCustomerOrderResponse(Customer customer) {
        Optional<List<Customer.Order>> optionalOrders = Optional.ofNullable(customer.getOrders());
        List<CustomerResponse.OrderResponse> orderList = optionalOrders
                .orElse(Collections.emptyList())
                .stream()
                .map(CustomerApiMapper::mapToCustomerOrderResponse)
                .toList();

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getCompanyName(),
                orderList
        );
    }

    private static CustomerResponse.OrderResponse mapToCustomerOrderResponse(Customer.Order orderOfCustomer) {
        return new CustomerResponse.OrderResponse(
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

    public static CreateCustomerDto mapToCreateCustomerDto(CreateCustomerRequest createCustomerRequest) {
        return new CreateCustomerDto(
                createCustomerRequest.getFirstName(),
                createCustomerRequest.getLastName(),
                createCustomerRequest.getPhone(),
                createCustomerRequest.getEmail(),
                createCustomerRequest.getCompanyName()
        );
    }
}
