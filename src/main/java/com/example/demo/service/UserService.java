package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User registUser(User user) {
       return userRepository.save(user);
    }

    public Map<String,Object> getUserById(Long userId){
       Optional<User> userData= userRepository.findById(userId);
        Map<String,Object> user = new HashMap<>();
        if(userData.isPresent()){
            user.put("userName",userData.get().getUserName());
            user.put("email",userData.get().getEmail());
            user.put("bio",userData.get().getBio());
            
            
        }
        else{
        //    user.put("message","User not found witg Id "+ userId);
        
        user.put("message", "User not found with Id " + userId);
       
        }
        return user;

        // if(userData.isPresent()){
        //     return userData;
        // }
        // return null;
    }


    public User updateUser(Long userId, User user){
        Optional<User> existingUser=userRepository.findById(userId);

        if(existingUser.isPresent()){
           User userData= existingUser.get();
           if(userData.getUserName()!=null){
            userData.setUserName(user.getUserName());
           }
           if(userData.getEmail()!=null){
            userData.setEmail(user.getEmail());
           }
           if(userData.getBio()!=null){
            userData.setBio(user.getBio());
           }
           return userRepository.save(userData);

        }
        return null;

    }
}
