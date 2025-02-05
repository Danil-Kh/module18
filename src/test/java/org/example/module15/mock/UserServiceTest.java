package org.example.module15.mock;

import org.example.module15.entities.User;
import org.example.module15.exceptions.ExceptionMessages;
import org.example.module15.exceptions.FailedRegistrationException;
import org.example.module15.repositories.UserRepository;
import org.example.module15.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.example.module15.exceptions.FailedLoginException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    void findByUserName_ExistingUser_ReturnsUser() {

        String username = "testUser";
        User expectedUser = new User();
        expectedUser.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(expectedUser);

        User result = userService.findByUserName(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
        verify(userRepository).findByUsername(username);
    }

    @Test
    void findByUserName_NonexistentUser_ReturnsNull() {

        String username = "nonexistentUser";
        when(userRepository.findByUsername(username)).thenReturn(null);


        User result = userService.findByUserName(username);

        assertNull(result);
        verify(userRepository).findByUsername(username);
    }

    @Test
    void saveUser_ValidUser_SavesSuccessfully() {
        User user = new User();
        user.setUsername("validUser");
        user.setPassword("password123");
        when(userRepository.findByUsername(user.getUsername())).thenReturn(null);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");

        userService.saveUser(user);

        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(user);
        assertEquals("encodedPassword", user.getPassword());
    }

    @Test
    void saveUser_ExistingUsername_ThrowsException() {
        User user = new User();
        user.setUsername("existingUser");
        user.setPassword("password123");
        when(userRepository.findByUsername(user.getUsername())).thenReturn(new User());

        FailedRegistrationException exception = assertThrows(FailedRegistrationException.class,
                () -> userService.saveUser(user));
        assertEquals(ExceptionMessages.USERNAME_ALREADY_EXISTS.getMessage(), exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void saveUser_EmptyUsername_ThrowsException() {

        User user = new User();
        user.setUsername("");
        user.setPassword("password123");


        FailedRegistrationException exception = assertThrows(FailedRegistrationException.class,
                () -> userService.saveUser(user));
        assertEquals(ExceptionMessages.UNABLE_SAVE_USER_EMPTY_USERNAME.getMessage(), exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void saveUser_ShortPassword_ThrowsException() {

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("12345");


        FailedRegistrationException exception = assertThrows(FailedRegistrationException.class,
                () -> userService.saveUser(user));
        assertEquals(ExceptionMessages.PASSWORD_TO_SHORT.getMessage(), exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void saveUser_UsernameTooLong_ThrowsException() {

        User user = new User();
        user.setUsername("a".repeat(51));
        user.setPassword("password123");


        FailedRegistrationException exception = assertThrows(FailedRegistrationException.class,
                () -> userService.saveUser(user));
        assertEquals(ExceptionMessages.USERNAME_TOO_LONG.getMessage(), exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void saveUser_PasswordTooLong_ThrowsException() {

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("a".repeat(61));


        FailedRegistrationException exception = assertThrows(FailedRegistrationException.class,
                () -> userService.saveUser(user));
        assertEquals(ExceptionMessages.PASSWORD_TOO_LONG.getMessage(), exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void loginUser_ValidCredentials_SuccessfulLogin() {

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));


        assertDoesNotThrow(() -> userService.loginUser(user, authenticationManager));
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }


    @Test
    void loginUser_InvalidCredentials_ThrowsException() {

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("wrongPassword");
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        FailedLoginException exception = assertThrows(FailedLoginException.class,
                () -> userService.loginUser(user, authenticationManager));
        assertEquals(ExceptionMessages.USER_NOT_FOUND.getMessage(), exception.getMessage());
        verify(authenticationManager).authenticate(any());
    }
}