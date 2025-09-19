package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userName}")
    public User findUserByUserName(@PathVariable String userName) {
        return userService.findUserByUserName(userName);
    }

    @PutMapping()
    public ResponseEntity<?> updateUserByUserName(@RequestBody User newUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        try {
            User oldUser = userService.findUserByUserName(userName);
            if (oldUser != null) {
                oldUser.setUserName(newUser.getUserName());
                oldUser.setPassword(newUser.getPassword());
                userService.saveNewUser(oldUser);
            }
            return new ResponseEntity<>(oldUser, HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println("Error occured");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
