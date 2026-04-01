package com.abhishek.hotelautomation.service;

import com.abhishek.hotelautomation.model.Guest;
import com.abhishek.hotelautomation.repo.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

    @Autowired
    private GuestRepository repo;


    public Guest createGuest(Guest guest) {
        return repo.save(guest);
    }

    public List<Guest> getAllGuests(){
        return repo.findAll();
    }

    public Guest getGuestById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found"));
    }

    public Guest updateGuest(Integer id, Guest guest) {
        Guest existingGuest = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        existingGuest.setName(guest.getName());
        existingGuest.setEmail(guest.getEmail());
        existingGuest.setPhone(guest.getPhone());

        return repo.save(existingGuest);
    }

    public void deleteGuest(Integer id) {
        repo.deleteById(id);
    }
}
