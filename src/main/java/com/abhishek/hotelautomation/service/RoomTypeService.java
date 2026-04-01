package com.abhishek.hotelautomation.service;

import com.abhishek.hotelautomation.model.RoomType;
import com.abhishek.hotelautomation.repo.RoomTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {

    @Autowired
    private RoomTypeRepo repo;

    public RoomType addRoomType(RoomType roomType) {
        return repo.save(roomType);
    }

    public List<RoomType> getAllRoomTypes() {
        return repo.findAll();
    }
}
