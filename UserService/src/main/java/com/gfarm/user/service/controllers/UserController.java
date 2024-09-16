package com.gfarm.user.service.controllers;


import com.gfarm.user.service.entities.User;
import com.gfarm.user.service.impl.UserServiceImpl;
import com.gfarm.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.flogger.Flogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    //cretae
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
       User user1 =  userService.saveUser(user);

       return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    //single user
    @GetMapping("/{userId}")
   // @CircuitBreaker(name = "ratingHotelBreaker" , fallbackMethod = "ratingHotelFallBack")
   // @Retry(name = "ratingHotelService" , fallbackMethod = "ratingHotelFallBack")
    @RateLimiter(name= "userRateLimiter" , fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
    //creating fallback method for circuitbreaker
    public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex){
        ex.getMessage();
        User user = User.builder().userId("123123").email("dummy@gmail.com").name("dummy").about("i am dummy, as service is down").build();
        return new ResponseEntity<>(user , HttpStatus.OK);
    }
    //get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
         List<User> userList=userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

}
