package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

//Lavet af Joakim
@Entity
public class User {

    @Id
    private int user_id;
    private String username;
    private String password;
    private boolean enabled;
    private String role;

    public User() {
    }

    public User(int user_id, String username, String password, boolean enabled, String role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    // Returns true because a user is enabled by default and there's no way to disable one.
    public boolean isEnabled() {
        return true;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}