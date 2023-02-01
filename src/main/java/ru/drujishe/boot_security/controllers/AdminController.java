package ru.drujishe.boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.drujishe.boot_security.model.MyUser;
import ru.drujishe.boot_security.model.Role;
import ru.drujishe.boot_security.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;

    }

    public AdminController() {
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

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", Role.getAllRoles());
        return "/admin/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") MyUser myUser, @PathVariable("id") long id) {
        userService.update(id, myUser);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
