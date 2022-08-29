package com.catering.app.controller;

import com.catering.app.controller.dto.request.CreateCustomerDto;
import com.catering.app.controller.dto.response.CustomerDto;
import com.catering.app.controller.mapper.CustomerDtoMapper;
import com.catering.app.domain.models.Customer;
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
    @ApiResponse(code = 200, message = "", response = CustomerDto.class)
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAll()
                .stream()
                .map(CustomerDtoMapper::mapToCustomerDto)
                .toList();
    }

    @GetMapping("/{customerId}")
    @ApiOperation(value = "Получить заказчика по id")
    @ApiResponse(code = 200, message = "", response = CustomerDto.class)
    public CustomerDto getCustomerById(@PathVariable @NotNull Integer customerId) throws ServiceException {
        return CustomerDtoMapper.mapToCustomerDto(customerService.getCustomerById(customerId));
    }

    @DeleteMapping("/{customerId}")
    @ApiOperation(value = "Удалить заказчика по id")
    @ApiResponse(code = 200, message = "", response = CustomerDto.class)
    public void deleteCustomerById(@PathVariable @NotNull Integer customerId) throws ServiceException {
        customerService.deleteCustomerById(customerId);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Создать заказчика")
    @ApiResponse(code = 200, message = "", response = CustomerDto.class)
    public Customer createCustomer(@Valid @RequestBody CreateCustomerDto customer) {
        System.out.println();
        return customerService.createCustomer(CustomerDtoMapper.mapToCreateCustomer(customer));
    }
}
