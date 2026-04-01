package com.abhishek.hotelautomation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private Integer bookingId;
    private String guestName;
    private String roomNumber;
    private String roomType;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal totalPrice;
}
