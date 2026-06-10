package com.service.booking.service.impl;

import com.service.booking.controller.ServiceBookingController;
import com.service.booking.entity.Customer;
import com.service.booking.entity.ServiceBooking;
import com.service.booking.entity.Vehicle;
import com.service.booking.enums.BookingStatus;
import com.service.booking.model.request.BookingRequest;
import com.service.booking.model.response.BookingSummaryResponse;
import com.service.booking.model.response.CustomerBooking;
import com.service.booking.repository.CustomerRepository;
import com.service.booking.repository.ServiceBookingRepository;
import com.service.booking.service.ServiceBookingService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceBookingServiceImpl implements ServiceBookingService {

    public static final Logger logger = LoggerFactory.getLogger(ServiceBookingController.class);

    @Autowired
    ServiceBookingRepository serviceBookingRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    @Transactional
    public Long bookService(BookingRequest bookingRequest) throws Exception {
        validateBookingDateTime(bookingRequest);

        logger.debug("Building booking details for vehicle {} and customer {}", bookingRequest.getRegistrationNumber(), bookingRequest.getCustomerId());
        ServiceBooking serviceBooking = ServiceBooking.builder()
                .bookingDate(bookingRequest.getBookingDateTime())
                        .vehicle(Vehicle.builder().registrationNumber(bookingRequest.getRegistrationNumber()).customer(
                                Customer.builder().id(bookingRequest.getCustomerId()).build()).build())
                        .description(bookingRequest.getServiceDesc())
                        .status(BookingStatus.SCHEDULED.toString())
                        .build();
        logger.debug("Saving booking for vehicle {} and customer {}", bookingRequest.getRegistrationNumber(), bookingRequest.getCustomerId());
        Long bookingId = saveBooking(serviceBooking);
        logger.info("Booking scheduled for {} service at {}", bookingRequest.getServiceDesc(), bookingRequest.getBookingDateTime());
        return bookingId;
    }

    private Long saveBooking(ServiceBooking serviceBooking) {
        ServiceBooking serviceBooked;
        try {
            serviceBooked = serviceBookingRepository.save(serviceBooking);
        } catch (Exception e) {
            logger.error("The service booking failed, Please try again after sometime", e);
            throw new RuntimeException("The service booking failed, Please try again after sometime");
        }
        return serviceBooked.getId();
    }

    private void validateBookingDateTime(BookingRequest bookingRequest) throws Exception {
        logger.info("Validating the provided booking date time {} against our database", bookingRequest.getBookingDateTime());
        if(serviceBookingRepository.existsByBookingDate(bookingRequest.getBookingDateTime())) {
            logger.error("There is already a booking at this date time, please provide a different date-time slot");
            throw new Exception("There is already a booking at this date time, please provide a different date-time slot");
        }
    }

    @Override
    public List<CustomerBooking> fetchBooking(Long customerId) throws Exception {
        List<CustomerBooking> customerBookings;
        logger.info("Validating the provided customerId {} against our database", customerId);
        if(customerRepository.existsById(customerId)){
            logger.debug("Making a DB call to fetch all the bookings for customer {}", customerId);
            customerBookings = serviceBookingRepository.findBookingsByCustomerId(customerId);
            logger.info("Obtained all the bookings for customer {}", customerId);
        }else{
            logger.error("Invalid customer id, this customer does not exists in out booking application");
            throw new Exception("Invalid customer id, this customer does not exists in out booking application");
        }
        return customerBookings;
    }

    @Override
    public List<BookingSummaryResponse> bookingSummary(String bookingFromDate, String bookingToDate) throws Exception {
        logger.debug("Fetching the booking details from {} to {} ", bookingFromDate, bookingToDate);
        List<BookingSummaryResponse> customerBookings = serviceBookingRepository.findBookingStatusByDateRange(bookingFromDate, bookingToDate);
        logger.info("Obtained all the booking details from {} to {} ", bookingFromDate, bookingToDate);
        return customerBookings;
    }
}
