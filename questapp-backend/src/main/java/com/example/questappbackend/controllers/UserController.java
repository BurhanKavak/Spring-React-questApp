package com.example.questappbackend.controllers;

import com.example.questappbackend.entities.User;
import com.example.questappbackend.services.abs.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getOneUser (@PathVariable("userId") Long userId){
        return userService.getOneUser(userId);
    }

    @PostMapping
    public User createOneUser (@RequestBody User user) {
        return userService.createOneUser(user);
    }


    @PutMapping("/{userId}")
    public User updateOneUser (@RequestBody User newUser,@PathVariable("userId") Long userId){
       return userService.updateOneUser(newUser,userId);

    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser (@PathVariable("userId") Long userId){
        userService.deleteOneUser(userId);
    }

}
