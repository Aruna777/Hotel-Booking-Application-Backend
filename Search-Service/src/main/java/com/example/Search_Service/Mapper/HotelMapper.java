package com.example.Search_Service.Mapper;

import com.example.Search_Service.dto.HotelDto;
import com.example.Search_Service.model.Hotel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelMapper {
    public HotelDto convertToDto(Hotel hotel) {
        HotelDto dto = new HotelDto();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setDestination(hotel.getDestination());
        dto.setAddress(hotel.getAddress());
        dto.setPricePerNight(hotel.getPricePerNight());
        return dto;
    }

    public List<HotelDto> convertToDtoList(List<Hotel> hotels) {
        return hotels.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}
