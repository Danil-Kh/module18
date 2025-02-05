package org.example.module15.services;

import lombok.RequiredArgsConstructor;
import org.example.module15.exceptions.ExceptionMessages;
import org.example.module15.exceptions.FailedLoginException;
import org.example.module15.exceptions.FailedRegistrationException;
import org.example.module15.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.module15.entities.User;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final int MAX_USERNAME_LENGTH = 50;
    private static final int MAX_PASSWORD_LENGTH = 60;
    public User findByUserName(String username){
       return userRepository.findByUsername(username);
    }
    public void saveUser(User user){
        if (findByUserName(user.getUsername()) != null){
            throw new FailedRegistrationException(ExceptionMessages.USERNAME_ALREADY_EXISTS.getMessage());
        }
        if (user.getUsername().isEmpty()){
            throw new FailedRegistrationException(ExceptionMessages.UNABLE_SAVE_USER_EMPTY_USERNAME.getMessage());
        }
        if (user.getPassword().length() < 6){
            throw new FailedRegistrationException(ExceptionMessages.PASSWORD_TO_SHORT.getMessage());
        }
        if (user.getUsername().length() > MAX_USERNAME_LENGTH){
            throw new FailedRegistrationException(ExceptionMessages.USERNAME_TOO_LONG.getMessage());
        }
        if (user.getPassword().length() > MAX_PASSWORD_LENGTH){
            throw new FailedRegistrationException(ExceptionMessages.PASSWORD_TOO_LONG.getMessage());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public void loginUser(User user, AuthenticationManager authenticationManager){
        if (user == null){
            throw new FailedLoginException(ExceptionMessages.INVALID_USERNAME_OR_PASSWORD.getMessage());
        }
        if (findByUserName(user.getUsername()) == null){
            throw new FailedLoginException(ExceptionMessages.USER_NOT_FOUND.getMessage());
        }
        try {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            authenticationManager.authenticate(authentication);
        }catch (AuthenticationException e){
            throw new FailedLoginException(ExceptionMessages.USER_NOT_FOUND.getMessage());
        }
    }
}
