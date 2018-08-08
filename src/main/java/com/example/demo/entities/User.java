package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ruzieljonm on 31/07/2018.
 */

@Entity
@Table(name="user")
public class User implements Serializable{

    @Id
    @GeneratedValue
    @Column(name="userid")
    private int userid;

    @Column(name="usertype")
    private String usertype;


    @Column(name="fname")
    private String fname;


    @Column(name="lname")
    private String lname;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
