package com.example.demo.repositories;

import com.example.demo.models.Motorhome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorhomeRepoJPA extends JpaRepository<Motorhome, Integer> {
}
