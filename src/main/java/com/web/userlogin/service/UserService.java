package com.web.userlogin.service;

import com.web.userlogin.model.User;
import com.web.userlogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User save(User user) {
        User createdUser = userRepository.save(user);
        return createdUser;
    }
    public User getUserByUserName(String userName){
        System.out.println(userName);
        User user=userRepository.findByUsername(userName);
        return user;
    }
}

