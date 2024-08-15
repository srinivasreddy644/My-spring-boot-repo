package net.javaguides.springboot.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.javaguides.springboot.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
    UserRegistrationDto save(UserRegistrationDto registrationDto);
    UserRegistrationDto findByUsername(String username);
    UserRegistrationDto update(String username, UserRegistrationDto registrationDto);
}




