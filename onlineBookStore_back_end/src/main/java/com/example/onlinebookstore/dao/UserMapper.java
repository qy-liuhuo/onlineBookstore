package com.example.onlinebookstore.dao;

import com.example.onlinebookstore.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface UserMapper {
    User selectUserByUsername(@Param("username") String username);
    int addUser(User user);
}
