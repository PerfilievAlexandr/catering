package com.catering.app.controller;

import com.catering.app.model.api.request.CreateCustomerRequest;
import com.catering.app.model.api.response.CustomerResponse;
import com.catering.app.controller.mapper.CustomerApiMapper;
import com.catering.app.model.domain.Customer;
import com.catering.app.exception.ServiceException;
import com.catering.app.servise.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping()
    @ApiOperation(value = "Получить список заказчиков")
    @ApiResponse(code = 200, message = "", response = CustomerResponse.class)
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAll()
                .stream()
                .map(CustomerApiMapper::mapToCustomerOrderResponse)
                .toList();
    }

    @GetMapping("/{customerId}")
    @ApiOperation(value = "Получить заказчика по id")
    @ApiResponse(code = 200, message = "", response = CustomerResponse.class)
    public CustomerResponse getCustomerById(@PathVariable @NotNull Integer customerId) {
        return CustomerApiMapper.mapToCustomerOrderResponse(customerService.getCustomerById(customerId));
    }

    @DeleteMapping("/{customerId}")
    @ApiOperation(value = "Удалить заказчика по id")
    @ApiResponse(code = 200, message = "", response = CustomerResponse.class)
    public void deleteCustomerById(@PathVariable @NotNull Integer customerId) {
        customerService.deleteCustomerById(customerId);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Создать заказчика")
    @ApiResponse(code = 200, message = "", response = CustomerResponse.class)
    public Customer createCustomer(@Valid @RequestBody CreateCustomerRequest customer) {
        return customerService.createCustomer(CustomerApiMapper.mapToCreateCustomerDto(customer));
    }
}
