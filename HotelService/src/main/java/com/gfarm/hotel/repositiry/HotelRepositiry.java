package com.gfarm.hotel.repositiry;

import com.gfarm.hotel.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepositiry extends JpaRepository<Hotel , String> {

}
