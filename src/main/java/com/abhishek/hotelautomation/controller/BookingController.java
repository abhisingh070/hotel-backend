package com.abhishek.hotelautomation.controller;

import com.abhishek.hotelautomation.dto.BookingDTO;
import com.abhishek.hotelautomation.dto.BookingRequestDTO;
import com.abhishek.hotelautomation.model.Booking;
import com.abhishek.hotelautomation.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService service;

    // Create Booking
    @PostMapping
    public BookingDTO createBooking(@RequestBody BookingRequestDTO request){
        return service.createBooking(request);
    }

    // Get All Bookings
    @GetMapping
    public List<BookingDTO> getAllBookings(){
        return service.getAllBookings();
    }

    // Get Booking By Id
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Integer id){
        return service.getBookingById(id);
    }

    // Delete Booking
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Integer id){
        service.deleteBooking(id);
    }
}
