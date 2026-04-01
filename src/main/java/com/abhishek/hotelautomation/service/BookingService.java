package com.abhishek.hotelautomation.service;

import com.abhishek.hotelautomation.dto.BookingDTO;
import com.abhishek.hotelautomation.dto.BookingRequestDTO;
import com.abhishek.hotelautomation.model.Booking;
import com.abhishek.hotelautomation.model.Guest;
import com.abhishek.hotelautomation.model.Room;
import com.abhishek.hotelautomation.repo.BookingRepository;
import com.abhishek.hotelautomation.repo.GuestRepository;
import com.abhishek.hotelautomation.repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repo;

    @Autowired
    private GuestRepository guestRepo;

    @Autowired
    private RoomRepository roomRepo;

    public BookingDTO convertToDTO(Booking booking) {

        long days = java.time.temporal.ChronoUnit.DAYS.between(
                booking.getCheckInDate(),
                booking.getCheckOutDate()
        );

        BigDecimal pricePerNight = booking.getRoom().getPricePerNight();

        BigDecimal totalPrice = pricePerNight.multiply(BigDecimal.valueOf(days));

        return new BookingDTO(
                booking.getId(),
                booking.getGuest().getName(),
                booking.getRoom().getRoomNumber(),
                booking.getRoom().getRoomType().getName(),
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                totalPrice
        );
    }

    public BookingDTO createBooking(BookingRequestDTO request) {

        // Validate dates
        if(request.getCheckInDate().isAfter(request.getCheckOutDate()) ||
            request.getCheckInDate().isEqual(request.getCheckOutDate())){
            throw new RuntimeException("Invalid Booking dates");
        }

        //fetch guest
        Guest guest = guestRepo.findById((int) request.getGuestId())
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        // fetch room
        Room room = roomRepo.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        //Checking availability
        boolean alreadyBooked = repo
                .existsByRoomAndCheckInDateLessThanAndCheckOutDateGreaterThan(
                        room,
                        request.getCheckOutDate(),
                        request.getCheckInDate()
                );

        if (alreadyBooked) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Room already booked for selected dates"
            );
        }

        // 5. Creating Booking entity manually
        Booking booking = new Booking();
        booking.setGuest(guest);
        booking.setRoom(room);
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());

        booking.setStatus("BOOKED");


        Booking saved =  repo.save(booking);

        return convertToDTO(saved);
    }

    public List<BookingDTO> getAllBookings() {
        return repo.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    public Booking getBookingById(Integer id){
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public void deleteBooking(Integer id){
        repo.deleteById(id);
    }
}
