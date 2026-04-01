package com.abhishek.hotelautomation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

        private Integer id;
        private String roomNumber;
        private BigDecimal pricePerNight;
        private String roomType;
        private String imageUrl;


}

