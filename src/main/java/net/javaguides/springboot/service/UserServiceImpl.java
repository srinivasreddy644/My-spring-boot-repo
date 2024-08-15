package net.javaguides.springboot.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRegistrationDto save(UserRegistrationDto registrationDto) {
        User user = new User(
            registrationDto.getUsername(),
            registrationDto.getFirstName(),
            registrationDto.getLastName(),
            registrationDto.getEmail(),
            registrationDto.getMobile(),
            passwordEncoder.encode(registrationDto.getPassword()),
            Arrays.asList(new Role("ROLE_USER"))
        );
        User savedUser = userRepository.save(user);

        // Convert saved User to UserRegistrationDto
        return convertToDto(savedUser);
    }

    @Override
    public UserRegistrationDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        // Convert User to UserRegistrationDto
        return convertToDto(user);
    }

    @Override
    public UserRegistrationDto update(String username, UserRegistrationDto registrationDto) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser == null) {
            throw new IllegalArgumentException("User not found");
        }

        existingUser.setFirstName(registrationDto.getFirstName());
        existingUser.setLastName(registrationDto.getLastName());
        existingUser.setEmail(registrationDto.getEmail());
        existingUser.setMobile(registrationDto.getMobile());
        
        if (registrationDto.getPassword() != null && !registrationDto.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        }

        User updatedUser = userRepository.save(existingUser);

        // Convert updated User to UserRegistrationDto
        return convertToDto(updatedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private UserRegistrationDto convertToDto(User user) {
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setMobile(user.getMobile());
        return userDto;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}

