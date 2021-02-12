package com.example.jwt;

import com.example.jwt.dao.MyRoleRepository;
import com.example.jwt.dao.MyUserRepository;
import com.example.jwt.models.MyRole;
import com.example.jwt.models.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
public class JwtApplication implements CommandLineRunner {

    @Autowired
    private MyUserRepository userRepository;
    @Autowired
    private MyRoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }



    @Bean
    public BCryptPasswordEncoder getBPEC() {
        return new BCryptPasswordEncoder();
    }

    private void initRoles() {
        MyRole role1 = new MyRole();
        role1.setRole("USER");
        roleRepository.save(role1);

        MyRole role2 = new MyRole();
        role1.setRole("ADMIN");
        roleRepository.save(role2);
    }

    @Override
    public void run(String... args) throws Exception {

        initRoles();


        Collection<MyRole> user1Roles = new ArrayList<>();
        user1Roles.add(roleRepository.findMyRoleByRole("USER"));

        Collection<MyRole> user2Roles = new ArrayList<>();
        user1Roles.add(roleRepository.findMyRoleByRole("ADMIN"));


        MyUser user1 = new MyUser();
        user1.setUsername("user1");
        String password = new BCryptPasswordEncoder().encode("1234");
        user1.setPassword(password);
        user1.setRoles(user1Roles);
        userRepository.save(user1);

        MyUser user2 = new MyUser();
        user2.setUsername("user2");
        String password2 = new BCryptPasswordEncoder().encode("1234");
        user2.setPassword(password2);
        user2.setRoles(user2Roles);
        userRepository.save(user2);

    }
}
