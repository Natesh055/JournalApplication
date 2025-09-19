package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UserService userService;

    @GetMapping("/health-check")
    public String healthCheck() {
        System.out.println("Health ok");
        return ("Health ok");
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        try {
            userService.saveNewUser(user);
            System.out.println("User saved succesfully");
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println("Error occured");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

