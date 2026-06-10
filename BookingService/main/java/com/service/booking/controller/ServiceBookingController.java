package com.service.booking.controller;

import com.service.booking.model.request.BookingRequest;
import com.service.booking.model.response.BookingSummaryResponse;
import com.service.booking.model.response.CustomerBooking;
import com.service.booking.service.ServiceBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/booking")
public class ServiceBookingController {

    public static final Logger logger = LoggerFactory.getLogger(ServiceBookingController.class);

    @Autowired
    ServiceBookingService serviceBookingService;

    @PostMapping("/service")
    public ResponseEntity<Long> bookService(@RequestBody BookingRequest bookingRequest) throws Exception {
        logger.info("Initiating a booking for vehicle {} belonging to customer {}", bookingRequest.getRegistrationNumber(), bookingRequest.getCustomerId());
        Long bookingId = serviceBookingService.bookService(bookingRequest);
        logger.info("Successfully booked a service for vehicle {} belonging to customer {}", bookingRequest.getRegistrationNumber(), bookingRequest.getCustomerId());
        return new ResponseEntity<>(bookingId, HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CustomerBooking>> fetchBooking(@PathVariable(value = "customerId") Long customerId) throws Exception {
        logger.info("Fetching all the bookings for customer {}", customerId);
        List<CustomerBooking> customerBooking = serviceBookingService.fetchBooking(customerId);
        logger.info("Fetched all the bookings belonging to the vehicles of customer {}", customerId);
        return new ResponseEntity<>(customerBooking, HttpStatus.OK);
    }

    @GetMapping("/summary")
    public ResponseEntity<List<BookingSummaryResponse>> bookingSummary(@RequestParam(value = "bookingFromDate") String bookingFromDate,
                                                                       @RequestParam(value = "bookingToDate") String bookingToDate) throws Exception {
        logger.info("Fetching the booking details from {} to {} ", bookingFromDate, bookingToDate);
        List<BookingSummaryResponse> bookingSummary = serviceBookingService.bookingSummary(bookingFromDate, bookingToDate);
        logger.info("Fetched the booking details from {} to {} ", bookingFromDate, bookingToDate);
        return new ResponseEntity<>(bookingSummary, HttpStatus.OK);
    }

}
