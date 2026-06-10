package com.service.booking.service;

import com.service.booking.model.request.BookingRequest;
import com.service.booking.model.response.BookingSummaryResponse;
import com.service.booking.model.response.CustomerBooking;

import java.util.List;

public interface ServiceBookingService {
    Long bookService(BookingRequest bookingRequest) throws Exception;

    List<CustomerBooking> fetchBooking(Long customerId) throws Exception;

    List<BookingSummaryResponse> bookingSummary(String bookingFromDate, String bookingToDate) throws Exception;

}
