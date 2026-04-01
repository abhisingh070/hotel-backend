package com.abhishek.hotelautomation.controller;

import com.abhishek.hotelautomation.model.Room;
import com.abhishek.hotelautomation.model.RoomType;
import com.abhishek.hotelautomation.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room-types")
@CrossOrigin(origins = "*")
public class RoomTypeController {

    @Autowired
    private RoomTypeService service;

    @PostMapping
    public RoomType addRoomType(@RequestBody RoomType roomType){
        return service.addRoomType(roomType);
    }

    @GetMapping
    public List<RoomType> getAllRoomTypes(){
        return service.getAllRoomTypes();
    }

}
