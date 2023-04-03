package com.example.questappbackend.services.abs;

import com.example.questappbackend.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getOneUserById(Long userId);

    User createOneUser(User user);

    User updateOneUser(User newUser,Long userId);

    void deleteOneUser(Long userId);

    User getOneUserByUserName(String userName);
}
