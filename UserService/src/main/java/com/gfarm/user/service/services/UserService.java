package com.gfarm.user.service.services;

import com.gfarm.user.service.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    //create
    User saveUser(User user);
    //get all user
    List<User> getAllUsers();
    //get single user of given id
    User getUser(String userId);
}
