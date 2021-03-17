package com.example.orderdemo.service;

import com.example.orderdemo.entity.User;
import com.example.orderdemo.repository.UserRepository;
import com.example.orderdemo.security.UserDetailImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSevice implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(s);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(s);
        }
        return UserDetailImp.build(user.get());
    }
}
