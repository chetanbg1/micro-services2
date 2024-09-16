package com.gfarm.openfeign.external;

import com.gfarm.openfeign.entities.Entity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "feignCall", url = "https://jsonplaceholder.typicode.com/posts")
public interface UserService {
    @GetMapping
    public List<Entity> getUsers();
}
