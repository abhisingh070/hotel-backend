package com.abhishek.hotelautomation.service;
import com.abhishek.hotelautomation.dto.RoomDTO;
import com.abhishek.hotelautomation.model.Room;
import com.abhishek.hotelautomation.repo.BookingRepository;
import com.abhishek.hotelautomation.repo.RoomRepository;
import com.abhishek.hotelautomation.repo.RoomTypeRepo;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repo;

    @Autowired
    private RoomTypeRepo roomTypeRepo;

    @Autowired
    private BookingRepository bookingRepository;

    public RoomDTO convertToDTO(Room room) {
        return new RoomDTO(
                room.getId(),
                room.getRoomNumber(),
                room.getPricePerNight(),
                room.getRoomType().getName(),
                room.getImageUrl()
        );
    }

    public Room addRoom(Room room) {

        Integer roomTypeId = room.getRoomType().getId();

        var roomType = roomTypeRepo.findById(roomTypeId)
                .orElseThrow(() -> new RuntimeException("Room type not found"));

        room.setRoomType(roomType);

        return repo.save(room);
    }

    public List<RoomDTO> getAllRooms() {
        return repo.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }


    public Room getRoom(long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Room not Found."));
    }

    public void deleteRoom(Long id) {
        repo.deleteById(id);
    }

    public Room updateRoom(Long id, Room room) {

        Room existingRoom = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        existingRoom.setRoomNumber(room.getRoomNumber());
        existingRoom.setPricePerNight(room.getPricePerNight());
        existingRoom.setStatus(room.getStatus());
        existingRoom.setCapacity(room.getCapacity());
        existingRoom.setDescription(room.getDescription());
        existingRoom.setImageUrl(room.getImageUrl());

        //Fetch room type from DB
        Integer roomTypeId = room.getRoomType().getId();

        var roomType = roomTypeRepo.findById(roomTypeId)
                .orElseThrow(() -> new RuntimeException("Room type not found"));

        existingRoom.setRoomType(roomType);

        return repo.save(existingRoom);
    }

    // Find available rooms
    public List<RoomDTO> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {

        List<Long> bookedRoomIds = bookingRepository
                .findBookedRoomIds(checkIn, checkOut);

        List<Room> rooms;

        if (bookedRoomIds.isEmpty()) {
            rooms = repo.findAll();
        } else {
            rooms = repo.findByIdNotIn(bookedRoomIds);
        }

        return rooms.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
