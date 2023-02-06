package ru.drujishe.boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.drujishe.boot_security.model.Person;
import ru.drujishe.boot_security.model.Role;
import ru.drujishe.boot_security.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsers(Principal principal, Model model) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("person", userService.getPersonByUsername(principal.getName()));
        model.addAttribute("roles",Role.getAllRoles());
        return "/admin/index";
    }

    @GetMapping(value = "/new")
    public String createPage(Principal principal,Model model) {
        model.addAttribute("user", new Person());
        model.addAttribute("person", userService.getPersonByUsername(principal.getName()));
        model.addAttribute("roles", Role.getAllRoles());
        return "/admin/new";
    }

    @PostMapping(value = "/new")
    public String createUser(@ModelAttribute("user") Person person) {
        userService.add(person);
        return "redirect:/admin";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") Person person, @PathVariable("id") long id) {
        System.out.println(person);
        userService.update(id, person);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
