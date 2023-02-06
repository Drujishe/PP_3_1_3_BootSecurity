package ru.drujishe.boot_security.controllers;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.drujishe.boot_security.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String show(Principal principal, Model model) {
        model.addAttribute("person", userService.getPersonByUsername(principal.getName()));
        return "/user/personal_page";
    }
}
