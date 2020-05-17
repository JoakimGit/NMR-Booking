package com.example.demo.repositories;

import com.example.demo.models.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepoJPA extends JpaRepository<Login, Integer> {
}
