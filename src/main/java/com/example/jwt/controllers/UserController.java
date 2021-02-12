package com.example.jwt.controllers;


import com.example.jwt.dao.MyRoleRepository;
import com.example.jwt.dao.MyUserRepository;
import com.example.jwt.models.MyRole;
import com.example.jwt.models.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

    @Autowired
    MyUserRepository userRepository;
    @Autowired
    MyRoleRepository roleRepository;

    @GetMapping("/users")
    public List<MyUser> users() {
        return userRepository.findAll();
    }


    @PostMapping(value = "/register")
    public MyUser register(@RequestBody MyUser user) {
        Collection<MyRole> userRoles = new ArrayList<>();
        userRoles.add(roleRepository.findMyRoleByRole("USER"));
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        user.setRoles(userRoles);
        return userRepository.save(user);
    }

}
