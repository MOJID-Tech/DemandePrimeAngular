package com.gta.remuniration.security;

import com.gta.remuniration.entity.User;
import com.gta.remuniration.exception.NotFoundException;

import com.gta.remuniration.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository users) {
        this.userRepository = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        return userRepository.findByLogin(username).orElseThrow(()
                -> new NotFoundException(User.class, "login", username));
    }
}