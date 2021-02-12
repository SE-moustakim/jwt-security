package com.example.jwt.dao;

import com.example.jwt.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    public MyUser findMyUsersByUsername(String username);
}
