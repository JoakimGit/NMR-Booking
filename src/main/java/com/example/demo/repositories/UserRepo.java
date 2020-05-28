package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {

    @Autowired
    JdbcTemplate template;

    public void createUser(User user) {
        String sql = "INSERT INTO user VALUES (?, ?, ?, ?, ?)";
        template.update(sql, user.getUser_id(), user.getUsername(), user.getPassword(), user.isEnabled(), user.getRole());
    }
}
