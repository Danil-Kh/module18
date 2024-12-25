package org.example.module15.controllers;

import lombok.AllArgsConstructor;
import org.example.module15.controllers.jwt.JwtUtil;
import org.example.module15.entities.User;
import org.example.module15.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class LoginController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {

        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        authenticationManager.authenticate(authInputToken);

        String token = jwtUtil.generateToken(user.getUsername());

        return ResponseEntity.ok(token);
    }

}
