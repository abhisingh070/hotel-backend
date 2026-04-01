package com.abhishek.hotelautomation.repo;

import com.abhishek.hotelautomation.model.Booking;
import com.abhishek.hotelautomation.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    boolean existsByRoomAndCheckInDateLessThanAndCheckOutDateGreaterThan(
            Room room,
            LocalDate checkOutDate,
            LocalDate checkInDate
    );

    @Query("SELECT b.room.id FROM Booking b " +
            "WHERE b.checkInDate < :checkOutDate " +
            "AND b.checkOutDate > :checkInDate")
    List<Long> findBookedRoomIds(LocalDate checkInDate, LocalDate checkOutDate);
}
