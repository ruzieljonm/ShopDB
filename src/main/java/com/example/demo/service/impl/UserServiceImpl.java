package com.example.demo.service.impl;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ruzieljonm on 31/07/2018.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public void saveUser(User newuser) {
        userRepository.save(newuser);
    }

    public User findByFname(String fname) {
        return userRepository.findByFname(fname);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByUserid(int userid) {
        return userRepository.findByUserid(userid);
    }
}
