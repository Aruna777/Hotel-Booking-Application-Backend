package com.example.Search_Service.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
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
