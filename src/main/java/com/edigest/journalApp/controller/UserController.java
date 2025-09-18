package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/{userName}")
    public User findUserByUserName(@PathVariable String userName) {
        return userService.findUserByUserName(userName);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println("Duplicate user detected");
            return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUserByUserName(@RequestBody User newUser, @PathVariable String userName) {
        try {
            User oldUser = userService.findUserByUserName(userName);
            if (oldUser != null) {
                oldUser.setUserName(newUser.getUserName());
                oldUser.setPassword(newUser.getPassword());
                userService.saveUser(oldUser);
            }
            return new ResponseEntity<>(oldUser, HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println("Error occured");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
