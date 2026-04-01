package com.abhishek.hotelautomation.controller;

import com.abhishek.hotelautomation.dto.RoomDTO;
import com.abhishek.hotelautomation.model.Room;
import com.abhishek.hotelautomation.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = "*")
public class RoomController {

    @Autowired
    private RoomService service;

    @PostMapping
    public Room addRoom(@RequestBody Room room){
        return service.addRoom(room);
    }

    @GetMapping
    public List<RoomDTO> getAllRooms(){
        return service.getAllRooms();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable long id){
        return service.getRoom(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        service.deleteRoom(id);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return service.updateRoom(id, room);
    }

    @GetMapping("/available")
    public List<RoomDTO> getAvailableRooms(
            @RequestParam LocalDate checkIn,
            @RequestParam LocalDate checkOut) {

        return service.getAvailableRooms(checkIn, checkOut);
    }
}
