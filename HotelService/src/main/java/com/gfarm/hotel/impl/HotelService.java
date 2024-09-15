package com.gfarm.hotel.impl;

import com.gfarm.hotel.entities.Hotel;
import com.gfarm.hotel.exceptions.ResourceNotFoundException;
import com.gfarm.hotel.repositiry.HotelRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelService implements com.gfarm.hotel.service.HotelService {

    @Autowired
    private HotelRepositiry hotelRepositiry;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomUserId = UUID.randomUUID().toString();
        hotel.setId(randomUserId);
        return hotelRepositiry.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepositiry.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepositiry.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("hotel not found for given id" + hotelId));
    }
}
