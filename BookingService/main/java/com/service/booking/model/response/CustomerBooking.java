package com.service.booking.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class CustomerBooking {
    private String registrationNumber;
    private LocalDateTime bookingDateTime;
    private String serviceDesc;
    private String status;
}
