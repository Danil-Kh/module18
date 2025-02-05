package org.example.module15.controllers;

import lombok.AllArgsConstructor;
import org.example.module15.entities.User;
import org.example.module15.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class LoginController {
    private final UserService userService;
    AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "login";
        }
        userService.loginUser(user, authenticationManager);
        return "redirect:/list";
    }

}
