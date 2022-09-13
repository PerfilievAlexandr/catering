package com.catering.app.model.api.request;

import com.catering.app.model.enums.EventReason;
import com.catering.app.model.enums.EventType;
import com.catering.app.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateOrderRequest {
    @NotNull(message = "Status is required")
    private OrderStatus status;
    @NotBlank(message = "EventDate is required")
    private String eventDate;
    @NotNull(message = "EventReason is required")
    private EventReason eventReason;
    @NotNull(message = "PersonsQuantity is required")
    private Integer personsQuantity;
    @NotNull(message = "EventType is required")
    private EventType eventType;
    @NotBlank(message = "Address is required")
    private String address;
    private String comment;
    @NotNull(message = "CustomerId is required")
    private Integer customerId;
}
