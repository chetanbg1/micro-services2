package com.gfarm.rarting.service;

import com.gfarm.rarting.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RatingService {
    //create
    public Rating createRating(Rating rating);
    //all ratings
    public List<Rating> getAllRating();
    //get all by userid
    public List<Rating> getRatingByUserId(String userId);
    //get all by hotel
    public List<Rating> getRatingByHotelId(String hotelId);
}
