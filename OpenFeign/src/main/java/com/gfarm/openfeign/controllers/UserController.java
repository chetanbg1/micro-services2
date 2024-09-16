package com.gfarm.openfeign.controllers;

import com.gfarm.openfeign.entities.Entity;
import com.gfarm.openfeign.external.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

//    @Autowired
//    private UserService userService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Entity>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }
}
