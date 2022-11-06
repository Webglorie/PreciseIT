package com.preciseIT.entities;

public class User {
    private String username;
    private String password;
    private String secret;

    public User(String username, String password, String secret) {
        this.username = username;
        this.password = password;
        this.secret = secret;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSecret() {
        return secret;
    }
}