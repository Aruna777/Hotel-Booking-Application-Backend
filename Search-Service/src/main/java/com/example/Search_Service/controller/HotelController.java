package com.example.Search_Service.controller;

import com.example.Search_Service.dto.HotelDto;
import com.example.Search_Service.service.HotelService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/search")
    public Flux<HotelDto> searchHotels(
            @RequestParam String destination,
            @RequestParam LocalDate checkInDate,
            @RequestParam LocalDate checkOutDate,
            @RequestParam int guests) {

        return hotelService.searchHotels(destination, checkInDate, checkOutDate, guests);
    }
}
