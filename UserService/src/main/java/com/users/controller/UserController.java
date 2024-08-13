package com.users.controller;

import com.users.persistence.entity.User;
import com.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUser(username);
    }

    @GetMapping("/transaction")
    public String initiateTransaction(@RequestParam String userId, @RequestParam String amount) {
        return userService.initiateTransaction(userId, amount);
    }
}