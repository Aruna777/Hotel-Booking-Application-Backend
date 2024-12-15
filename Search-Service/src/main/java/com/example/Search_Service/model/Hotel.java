package com.example.Search_Service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table("search")
public class Hotel {

    @Id
    private Long id;
    private String name;
    private String destination;
    private String address;
    private Double pricePerNight;
    private Integer availableRooms;

}
