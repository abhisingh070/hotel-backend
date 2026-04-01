package com.abhishek.hotelautomation.repo;

import com.abhishek.hotelautomation.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepo extends JpaRepository<RoomType, Integer> {

}
