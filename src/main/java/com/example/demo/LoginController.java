package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String showLoginForm() {
        return "login"; // login.html page
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model
    ) {
        if ("admin".equals(username) && "1234".equals(password)) {
            return "redirect:/library"; // successful login → library books page
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // back to login page with error
        }
    }
}
