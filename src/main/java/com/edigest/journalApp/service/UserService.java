package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void saveUser(User user)
    {
        userRepository.save(user);
    }
    public List<User> getAllUser()
    {
        return userRepository.findAll();
    }
    public User findUserByUserName(String userName)
    {
        return userRepository.findByUserName(userName);
    }
    public void deleteUser(String userName)
    {
        userRepository.deleteById(userName);
    }
}
