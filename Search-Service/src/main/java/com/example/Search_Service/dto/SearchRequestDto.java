package com.example.Search_Service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchRequestDto {
    private String destination;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int guests;
}

