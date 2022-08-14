package com.catering.app.domain.models.customer;

import com.catering.app.domain.enums.EventReason;
import com.catering.app.domain.enums.EventType;
import com.catering.app.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderOfCustomer {
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
