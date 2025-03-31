package com.mycreation.jobsPortal.services;

import com.mycreation.jobsPortal.model.User;
import com.mycreation.jobsPortal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user){
       return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<User> authenticateUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.filter(u -> u.getPassword().equals(password));
    }

}
