package com.example.service;

import com.example.entity.userEntity;
import com.example.entity.userPrinciple;
import com.example.repo.userRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class userService implements UserDetailsService {
    private final userRepo userRepo;
    // i don not make bean at config because i think i will use it once at
    // this place when i register new user
    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(12);
    public userService(userRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userEntity user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(" User not founded " + username);
        }
        return new userPrinciple(user);
    }

    public userEntity register(userEntity user) throws UsernameNotFoundException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UsernameNotFoundException("Username is already in use");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
}
