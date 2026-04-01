package com.abhishek.hotelautomation.controller;

import com.abhishek.hotelautomation.model.Guest;
import com.abhishek.hotelautomation.service.GuestService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
@CrossOrigin(origins = "*")
public class GuestController {

    @Autowired
    private GuestService service;

    @PostMapping
    public Guest createGuest(@RequestBody Guest guest){
        return service.createGuest(guest);
    }

    @GetMapping
    public List<Guest> getAllGuests(){
        return service.getAllGuests();
    }

    @GetMapping("/{id}")
    public Guest getGuestById(@PathVariable Integer id){
        return service.getGuestById(id);
    }

    @PutMapping("/{id}")
    public Guest UpdateGuest(@PathVariable Integer id, @RequestBody Guest guest){
        return service.updateGuest(id, guest);
    }

    @DeleteMapping("/{id}")
    public void deleteGuest(@PathVariable Integer id){
        service.deleteGuest(id);
    }


}
