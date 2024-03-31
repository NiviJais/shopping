package com.java.dev.shopping.app.Service;

import com.java.dev.shopping.app.Entity.User;
import com.java.dev.shopping.app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }
}
