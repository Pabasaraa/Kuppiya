package com.example.mad_app;

public class UsersHelperClass {
    private String name, username, email, password, key;

    public UsersHelperClass(){}

    public UsersHelperClass(String name, String username, String email, String password, String key) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
