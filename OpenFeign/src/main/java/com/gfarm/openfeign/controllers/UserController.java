package com.gfarm.openfeign.controllers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfarm.openfeign.entities.Entity;
import com.gfarm.openfeign.external.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

//    @Autowired
//    private UserService userService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Entity>> getUsers() throws IOException {

        //fetching data from external api using userService interface's getUsers method
        List<Entity> users = userService.getUsers();
        //json object printed in console and stored in file
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(users);
        System.out.println(jsonData);
        //storing the json file in local 
        objectMapper.writeValue(new File("D:\\cbgwork\\micro-services2\\data.json"),jsonData);

        //json to string
        String json = String.valueOf(objectMapper.readValue(jsonData , ArrayList.class));
        System.out.println(json);

        return ResponseEntity.ok(users);
    }


}
