package com.example.Search_Service.repository;

import com.example.Search_Service.model.Hotel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.r2dbc.repository.Query;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface HotelRepository extends ReactiveCrudRepository<Hotel, Long> {
    @Query("""
        SELECT * FROM hotels h
        WHERE h.destination = :destination
          AND h.available_rooms >= :roomsNeeded
          AND h.id NOT IN (
              SELECT b.hotel_id FROM bookings b
              WHERE (b.check_in_date < :checkOutDate AND b.check_out_date > :checkInDate)
          )
    """)
    Flux<Hotel> findAvailableHotels(String destination, int roomsNeeded, LocalDate checkInDate, LocalDate checkOutDate);
}
