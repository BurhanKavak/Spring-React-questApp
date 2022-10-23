package com.example.questappbackend.services;

import com.example.questappbackend.entities.User;
import com.example.questappbackend.repository.UserRepository;
import com.example.questappbackend.services.abs.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getOneUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User createOneUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateOneUser(User newUser, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            User foundUser = getOneUser(userId);
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        } else {
            return null;
        }
    }

    @Override
    public void deleteOneUser(Long userId) {
        userRepository.deleteById(getOneUser(userId).getId());

    }
}
