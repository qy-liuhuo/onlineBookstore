package com.example.onlinebookstore.service;

import com.example.onlinebookstore.dao.UserMapper;
import com.example.onlinebookstore.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        User user=userMapper.selectUserByUsername(username);
        return user;
    }

    @Override
    public Boolean judgePassword(String username, String password) {
       User user=getUserByUsername(username);
       return user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()));
    }

    @Override
    public Boolean register(String username, String password) {
        if(getUserByUsername(username)==null)
        {
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            User user=new User(username,md5Password);
            userMapper.addUser(user);
            return true;
        }
        else
        {
            return false;
        }

    }
}
