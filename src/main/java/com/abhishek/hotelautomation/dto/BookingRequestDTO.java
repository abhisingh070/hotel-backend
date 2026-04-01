package com.abhishek.hotelautomation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDTO {

    private long guestId;
    private long roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
