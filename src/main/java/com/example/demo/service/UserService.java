package com.example.demo.service;

import com.example.demo.entities.User;
import org.springframework.stereotype.Service;

/**
 * Created by ruzieljonm on 31/07/2018.
 */
@Service
public interface UserService {
    void saveUser(User newuser);


    User findByFname(String fname);

    User findByEmail(String email);

    User findByUserid(int userid);
}
