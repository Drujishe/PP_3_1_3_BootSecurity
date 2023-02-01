package ru.drujishe.boot_security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.drujishe.boot_security.model.MyUser;
import ru.drujishe.boot_security.model.Role;
import ru.drujishe.boot_security.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "/admin/index";
    }

    @GetMapping(value = "/new")
    public String createPage(Model model) {
        model.addAttribute("user", new MyUser());
        model.addAttribute("roles", Role.getAllRoles());
        return "/admin/new";
    }

    @PostMapping(value = "/new")
    public String createUser(@ModelAttribute("user") MyUser myUser) {
        userService.add(myUser);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/admin/personal_page";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", Role.getAllRoles());
        return "/admin/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") MyUser myUser, @PathVariable("id") int id) {
        userService.update(id, myUser);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
