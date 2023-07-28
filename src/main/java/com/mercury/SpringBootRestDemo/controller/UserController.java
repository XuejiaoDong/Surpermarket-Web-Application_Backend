package com.mercury.SpringBootRestDemo.controller;

import com.mercury.SpringBootRestDemo.bean.User;
import com.mercury.SpringBootRestDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}/role")
    public User updateUserRole(@PathVariable int id, @RequestParam int roleId) {
        return userService.updateUserRole(id, roleId);
    }

}
