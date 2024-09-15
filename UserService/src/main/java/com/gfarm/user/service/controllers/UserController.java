package com.gfarm.user.service.controllers;


import com.gfarm.user.service.entities.User;
import com.gfarm.user.service.impl.UserServiceImpl;
import com.gfarm.user.service.services.UserService;
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
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User user = userService.getUser(userId);

        return ResponseEntity.ok(user);
    }

    //get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
         List<User> userList=userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

}
