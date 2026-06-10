package com.service.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle", schema = "")
public class Vehicle {
    @Id
    @Column(name = "registration_number", nullable = false)
    private String registrationNumber;
    @Column(name = "make", nullable = false)
    private String make;
    @Column(name = "model", nullable = false)
    private String model;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
