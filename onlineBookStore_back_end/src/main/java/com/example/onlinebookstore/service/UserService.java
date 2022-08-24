package com.example.onlinebookstore.service;

import com.example.onlinebookstore.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;

public interface UserService {
    User getUserByUsername(String username);
    Boolean judgePassword(String username,String password);
    Boolean register(String username,String password);
}
