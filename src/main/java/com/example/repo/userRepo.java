package com.example.repo;

import com.example.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<userEntity,Integer> {

    userEntity  findByUsername(String username);
}
