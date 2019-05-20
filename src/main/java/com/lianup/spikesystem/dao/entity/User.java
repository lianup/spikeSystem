package com.lianup.spikesystem.dao.entity;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private int userId;
    private String name;
    private String password;


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
