package com.projectx.projectx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectx.projectx.dao.UserRepo;
import com.projectx.projectx.entities.User;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Transactional
    public boolean insertUser(User user) {
        if (!user.isEmpty()) {
            userRepo.save(user);
            return true;
        }

        return false;
    }

    public User getUserByEmail(String email) {
        User userOptional = userRepo.findByEmail(email);
        return userOptional;
    }

}
