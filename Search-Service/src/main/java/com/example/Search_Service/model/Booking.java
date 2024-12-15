package com.example.Search_Service.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;

@Getter
@Setter
@Data
@Table("bookings")
public class Booking {

    @Id
    private Long id;
    private Long hotelId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;


}
