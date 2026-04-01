package com.abhishek.hotelautomation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roomNumber;
    private BigDecimal pricePerNight;
    private String status;
    private Integer capacity;
    private String description;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

}
