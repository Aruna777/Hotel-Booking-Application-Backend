package com.example.Search_Service.service;

import com.example.Search_Service.Mapper.HotelMapper;
import com.example.Search_Service.Mapper.SearchRequestMapper;
import com.example.Search_Service.dto.HotelDto;
import com.example.Search_Service.dto.SearchRequestDto;
import com.example.Search_Service.model.Hotel;
import com.example.Search_Service.repository.HotelRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final SearchRequestMapper searchRequestMapper;

    public HotelService(HotelRepository hotelRepository, HotelMapper hotelMapper, SearchRequestMapper searchRequestMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
        this.searchRequestMapper = searchRequestMapper;
    }

    public Flux<HotelDto> searchHotels(String destination, LocalDate checkInDate, LocalDate checkOutDate, int guests) {

        SearchRequestDto searchRequestDto = searchRequestMapper.toSearchRequestDto(destination, checkInDate, checkOutDate, guests);

        int roomsNeeded = (int) Math.ceil(guests / 2.0);


        Flux<Hotel> hotels = hotelRepository.findAvailableHotels(
                searchRequestDto.getDestination(),
                roomsNeeded,
                searchRequestDto.getCheckInDate(),
                searchRequestDto.getCheckOutDate()
        );


        return hotels.map(hotelMapper::convertToDto);
    }

}
