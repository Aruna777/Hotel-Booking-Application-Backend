package com.example.Search_Service.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;
@Data
@Table("bookings")
public class Booking {

    @Id
    private Long id;

    @NotNull(message = "Hotel ID cannot be null")
    private Long hotelId;

    @NotNull(message = "Check-in date cannot be null")
    private LocalDate checkInDate;

    @NotNull(message = "Check-out date cannot be null")
    private LocalDate checkOutDate;
}
