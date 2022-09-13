package com.catering.app.model.api.response;

import com.catering.app.model.enums.EventReason;
import com.catering.app.model.enums.EventType;
import com.catering.app.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String companyName;
    private List<OrderResponse> orders;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderResponse {
        private int id;
        private int number;
        private OrderStatus status;
        private String eventDate;
        private EventReason eventReason;
        private Integer personsQuantity;
        private EventType eventType;
        private String address;
        private String comment;
    }
}
