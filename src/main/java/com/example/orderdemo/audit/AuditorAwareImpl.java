package com.example.orderdemo.audit;

import com.example.orderdemo.entity.User;
import com.example.orderdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getCurrentAuditor() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username);
    }
}
