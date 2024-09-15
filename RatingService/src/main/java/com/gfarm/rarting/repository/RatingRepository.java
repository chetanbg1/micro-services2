package com.gfarm.rarting.repository;

import com.gfarm.rarting.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RatingRepository extends MongoRepository<Rating,String> {

    //custom finder methods

    public List<Rating> findByUserId(String userId);

    public List<Rating> findByHotelId(String hotelId);
}
