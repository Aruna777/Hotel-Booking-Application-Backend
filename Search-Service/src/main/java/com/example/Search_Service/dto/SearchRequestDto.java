package com.example.Search_Service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Data
public class SearchRequestDto {
    private String destination;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int guests;

}

