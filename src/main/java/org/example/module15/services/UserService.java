package org.example.module15.services;

import lombok.RequiredArgsConstructor;
import org.example.module15.exceptions.ExceptionMessages;
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
            throw new FailedRegistrationException(ExceptionMessages.USERNAME_ALREADY_EXISTS.getMessage());
        }
        if (user.getPassword().length() < 6){
            throw new FailedRegistrationException(ExceptionMessages.PASSWORD_TO_SHORT.getMessage());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
