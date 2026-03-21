package com.medicalstore.controller;

import com.medicalstore.dto.SignupRequest;
import com.medicalstore.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/signup")
public class AuthController {

    @Autowired
    private final AdminService adminService;

    public AuthController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public String register(
            @Valid @ModelAttribute("signupRequest") SignupRequest request,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "signup";
        }

        adminService.registerUser(request);
        model.addAttribute("success", "Signup successful!");
        return "login";
    }
}
