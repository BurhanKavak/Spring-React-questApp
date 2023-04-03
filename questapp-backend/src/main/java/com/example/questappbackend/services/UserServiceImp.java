package com.example.questappbackend.services;

import com.example.questappbackend.entities.User;
import com.example.questappbackend.repository.UserRepository;
import com.example.questappbackend.services.abs.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

      UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getOneUserById(Long userId) {
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
            User foundUser = getOneUserById(userId);
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
        userRepository.deleteById(getOneUserById(userId).getId());

    }

    @Override
    public User getOneUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
