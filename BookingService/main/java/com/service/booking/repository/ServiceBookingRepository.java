package com.service.booking.repository;

import com.service.booking.entity.ServiceBooking;
import com.service.booking.model.response.BookingSummaryResponse;
import com.service.booking.model.response.CustomerBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ServiceBookingRepository extends JpaRepository<ServiceBooking, Long> {

    @Query(value = "Select new com.service.booking.model.response.CustomerBooking( " +
            "v.registrationNumber, sb.bookingDate, sb.description, sb.status) " +
            "from ServiceBooking sb inner join Vehicle v on sb.vehicle.registrationNumber = v.registrationNumber inner join " +
            "Customer c on v.customer.id = c.id where c.id = :customerId")
    List<CustomerBooking> findBookingsByCustomerId(Long customerId);

    boolean existsByBookingDate(LocalDateTime bookingDate);

    @Query(value = "Select new com.service.booking.model.response.BookingSummaryResponse( " +
            "sb.status, count(sb.status)) from ServiceBooking sb " +
            "where DATE(sb.bookingDate) >= DATE(:bookingFromDate)  AND DATE(sb.bookingDate) <= DATE(:bookingToDate) " +
            "group by sb.status")
    List<BookingSummaryResponse> findBookingStatusByDateRange(String bookingFromDate, String bookingToDate);

}
