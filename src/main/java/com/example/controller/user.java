package com.example.controller;

import com.example.entity.userEntity;
import com.example.service.userService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class user {
    private final userService userService;
    public user(userService userService) {
        this.userService = userService;
    }
    @GetMapping("/")
    public String user(HttpServletRequest request) {

        return "Hello World"+ request.getSession().getId();
    }
    @PostMapping("/user")
    public userEntity user(@RequestBody userEntity user)throws UsernameNotFoundException {
      userService.register(user);
      return user;
    }
}
