package com.gfarm.hotel.controller;

import com.gfarm.hotel.entities.Hotel;
import com.gfarm.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping()
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel hotel1= hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    @GetMapping()
    public ResponseEntity<List<Hotel>> getAllHotles(){
        List<Hotel> hotelList=hotelService.getAllHotels();
        return ResponseEntity.ok(hotelList);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
        Hotel hotel=hotelService.getHotel(hotelId);
        return  ResponseEntity.ok(hotel);
    }
}
