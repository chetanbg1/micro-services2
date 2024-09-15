package com.gfarm.user.service.repositories;

import com.gfarm.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , String> {

}
