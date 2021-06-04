package com.sportify.Sportify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InputController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        String loginError = "Вы ввели неправильный логин или пароль";
        model.addAttribute("loginError", loginError);
        return "login";
    }
}
