package com.example.Search_Service.Mapper;

import com.example.Search_Service.dto.SearchRequestDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SearchRequestMapper {

    public SearchRequestDto toSearchRequestDto(String destination, LocalDate checkInDate, LocalDate checkOutDate, int guests) {
        SearchRequestDto dto = new SearchRequestDto();
        dto.setDestination(destination);
        dto.setCheckInDate(checkInDate);
        dto.setCheckOutDate(checkOutDate);
        dto.setGuests(guests);
        return dto;
    }
}