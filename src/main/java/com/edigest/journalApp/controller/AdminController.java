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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers()
    {
        try {
            List<User> allUsers = userService.getAllUsers();
            if (allUsers != null && !allUsers.isEmpty()) {
                return new ResponseEntity<>(allUsers, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<?> createUserAdminRole(@RequestBody User user)
    {
        try {
            userService.createNewAdminUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
