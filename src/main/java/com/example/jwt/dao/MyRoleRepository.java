package com.example.jwt.dao;

import com.example.jwt.models.MyRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyRoleRepository extends JpaRepository<MyRole, Long> {
    public MyRole findMyRoleByRole(String role);
}
