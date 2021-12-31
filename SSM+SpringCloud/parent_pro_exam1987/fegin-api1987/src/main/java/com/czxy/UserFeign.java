package com.czxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("userservice")
public interface UserFeign {
    @PostMapping("/user")
    BaseResult login(@RequestBody User user);
    @GetMapping("/user/{uid}")
    User selectUserByUid(@PathVariable("uid") Integer uid);
}
