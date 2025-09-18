package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<User> saveNewUser(@RequestBody User user) {
        try {
            userService.saveNewUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println("Error occured");
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUserByUserName(@RequestBody User newUser, @PathVariable String userName) {
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
