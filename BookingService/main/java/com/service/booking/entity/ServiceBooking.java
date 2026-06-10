package com.service.booking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "service_booking", schema = "")
public class ServiceBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "status", nullable = false)
    private String status;
    @ManyToOne
    @JoinColumn(name = "vehicle_number")
    private Vehicle vehicle;
}
