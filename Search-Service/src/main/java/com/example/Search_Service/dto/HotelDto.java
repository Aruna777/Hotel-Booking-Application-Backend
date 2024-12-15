package com.example.Search_Service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class HotelDto {
    private Long id;
    private String name;
    private String destination;
    private String address;
    private Double pricePerNight;

}
