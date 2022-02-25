package com.homework.games.controllers;

import com.homework.games.entities.Role;
import com.homework.games.entities.User;
import com.homework.games.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Optional;

@Controller
public class SignUpController {

    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/register")
    public String signUp() {

        return "signUp";
    }

    @PostMapping(value = "/register")
    public String addUser(User user) {
        Optional<User> userFromDb = userService.findUserByUsername(user.getUsername());

        if (userFromDb.isPresent()) {
            System.out.println("Такой пользователь уже существует!");

            return "redirect:/register";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        userService.add(user);

        return "redirect:/auth/login";
    }
}
