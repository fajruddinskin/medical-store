package com.medicalstore.controller;

import com.medicalstore.dto.SignupRequest;
import com.medicalstore.entity.UserModel;
import com.medicalstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/signup")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String register(
            @Valid @ModelAttribute("signupRequest") SignupRequest request,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "signup";
        }

        userService.registerUser(request);
        model.addAttribute("success", "Signup successful!");
        return "login";
    }
}
