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
public class RegisterController {
    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user,
                               RedirectAttributes redirectAttributes) {
        if (user.getPassword().length() < 6) {
            redirectAttributes.addFlashAttribute("invalidPassword", "Password must be at least 6 characters");
            return "redirect:/register";
        }
        try{
            userService.saveUser(user);
        }
        catch (Exception e) {
            redirectAttributes.addFlashAttribute("invalidFeedback", e.getMessage());
            return "redirect:/register";
        }

        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/login";
    }
}
