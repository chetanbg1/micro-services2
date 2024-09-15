package com.gfarm.user.service.impl;

import com.gfarm.user.service.entities.Hotel;
import com.gfarm.user.service.entities.Rating;
import com.gfarm.user.service.entities.User;
import com.gfarm.user.service.exceptions.ResourceNotFoundException;
import com.gfarm.user.service.external.services.HotelService;
import com.gfarm.user.service.repositories.UserRepository;
import com.gfarm.user.service.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //create a bean in configuration class
    //create new configuration class or define in main class
    //@LoadBalanced -use this annotation to call the service from discovery client / no hardcoding of urls
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user with geiven id is not found on server" + userId));

        //http://localhost:8083/rating/users/017c7872-e9f4-4324-9814-014f7f9bb15f
        //fetch rating of the above user from rating service

        //Rating[] ratingOfUser = restTemplate.getForObject("http://localhost:8083/rating/users/" + user.getUserId(), Rating[].class);
        //remove hardcoded url
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/rating/users/" + user.getUserId(), Rating[].class);


        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
// using rest template
//        List<Rating> ratingList = ratings.stream().map(rating -> {
//            //api call to hotel service to get the hotel
//            //http://localhost:8082/hotels/32f1efa8-c498-4442-82fd-df4489cc0f7e
//           //ResponseEntity<Hotel> hotel = restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId() , Hotel.class);
//            //remove hardcoded url
//            ResponseEntity<Hotel> hotel = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId() , Hotel.class);
//
//           Hotel body  = hotel.getBody();
//            rating.setHotel(body);
//           return rating;
//        }).toList();

        //using feign client
        List<Rating> ratingList = ratings.stream().map(rating -> {
            Hotel body = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(body);
            return rating;
        }).toList();

        user.setRatings(ratingList);
        return user;
    }
}
