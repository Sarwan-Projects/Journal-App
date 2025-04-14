package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController
{
    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String health()
    {
        return "ok";
    }

    @PostMapping("/create-user")
    public void saveUser(@RequestBody User user)
    {
        userService.saveNewUser(user);
    }
}
