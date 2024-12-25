package org.example.module15.services;

import lombok.RequiredArgsConstructor;
import org.example.module15.exceptions.FailedRegistrationException;
import org.example.module15.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.module15.entities.User;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public User findByUserName(String username){
        return userRepository.findByUsername(username);
    }
    public void saveUser(User user){
       if (findByUserName(user.getUsername()) != null){
           throw new FailedRegistrationException("Username already exists");
       }
     if (user.getPassword().length() < 6){
           throw new FailedRegistrationException("Password must be at least 6 characters");
     }
     user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
