package org.example.module15.services;

import lombok.RequiredArgsConstructor;
import org.example.module15.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.example.module15.entities.User;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    public User findByUserName(String username){
        return userRepository.findByUsername(username);
    }
}
