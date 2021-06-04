package com.sportify.Sportify.controller;

import com.sportify.Sportify.model.UserDTO;
import com.sportify.Sportify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("userForm") UserDTO userForm, Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "/registration";
        } else {

            if (userService.saveUser(userForm)) {
                return "redirect:/login";
            } else {
                String errorLogin = "Такой логин уже есть";
                model.addAttribute("errorLogin", errorLogin);
                return "/registration";
            }
        }
    }

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}
