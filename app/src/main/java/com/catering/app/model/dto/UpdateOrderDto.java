package com.catering.app.model.dto;

import com.catering.app.model.enums.EventReason;
import com.catering.app.model.enums.EventType;
import com.catering.app.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderDto {
    private int id;
    private OrderStatus status;
    private String eventDate;
    private EventReason eventReason;
    private Integer personsQuantity;
    private EventType eventType;
    private String address;
    private String comment;
    private Integer customerId;
}
