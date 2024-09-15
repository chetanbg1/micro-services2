package com.gfarm.hotel.service;

import com.gfarm.hotel.HotelServiceApplication;
import com.gfarm.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    public Hotel createHotel(Hotel hotel);
    public List<Hotel> getAllHotels();
    public Hotel getHotel(String hotelId);
}
