package com.example.demo.repository;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ruzieljonm on 01/08/2018.
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByFname(String fname);

    User findByEmail(String email);

    User findByUserid(int userid);
}
