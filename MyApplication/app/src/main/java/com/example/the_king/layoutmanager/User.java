package com.example.the_king.layoutmanager;

import com.orm.SugarRecord;

/**
 * Created by The_King on 5/11/2017.
 */

public class User extends SugarRecord {
    private String username;
    private String password;
    private String email;
    public User(){
    }

    public User(String username, String password,String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
