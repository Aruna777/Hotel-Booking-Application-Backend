package com.example.Search_Service.repository;

import com.example.Search_Service.model.Booking;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookingRepository  extends ReactiveCrudRepository<Booking, Long> {
}
