package com.example.Search_Service.dto;

import lombok.Data;

@Data
public class HotelDto {
    private Long id;
    private String name;
    private String destination;
    private String address;
    private Double pricePerNight;

}
