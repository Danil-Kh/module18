package org.example.module15.controllers;

import lombok.AllArgsConstructor;
import org.example.module15.entities.User;
import org.example.module15.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping("/login")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
    @PostMapping("/login")
    public String registerUser( @ModelAttribute("user") User user,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "login";
        }

        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/list";
    }

}
