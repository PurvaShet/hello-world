package com.service.booking.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BookingSummaryResponse {
    private String Status;
    private Long bookingCount;
}

