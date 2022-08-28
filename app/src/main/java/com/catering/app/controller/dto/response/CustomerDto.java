package com.catering.app.controller.dto.response;

import com.catering.app.domain.enums.EventReason;
import com.catering.app.domain.enums.EventType;
import com.catering.app.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String companyName;
    private List<OrderDto> orders;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDto {
        private int id;
        private String number;
        private OrderStatus status;
        private String eventDate;
        private EventReason eventReason;
        private Integer personsQuantity;
        private EventType eventType;
        private String address;
        private String comment;
    }
}
