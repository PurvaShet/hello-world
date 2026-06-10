package com.service.booking.model.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingRequest {
    private Long customerId;
    private String registrationNumber;
    private String serviceDesc;
    private LocalDateTime bookingDateTime;
}
