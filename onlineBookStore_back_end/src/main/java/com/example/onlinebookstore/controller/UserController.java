package com.example.onlinebookstore.controller;

import com.alibaba.fastjson.JSON;
import com.example.onlinebookstore.common.PermissionAnnotation;
import com.example.onlinebookstore.common.Response;
import com.example.onlinebookstore.dao.UserMapper;
import com.example.onlinebookstore.domain.User;
import com.example.onlinebookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    Response response;
    @Autowired
    UserMapper userMapper;

    /**
     * 登录接口
     * @param username
     * @param password
     * @param httpSession
     * @return Response
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam String username,@RequestParam String password,HttpSession httpSession)
    {
        if(userService.judgePassword(username,password))
        {
            httpSession.setAttribute("username",username);
            return response.toJSON(200,"登录成功");
        }
        else
        {
            return response.toJSON(402,"登录失败");
        }
    }

    /**
     * 注册接口
     * @param username
     * @param password
     * @param confirmPassword
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public String register(@RequestParam String username,@RequestParam String password,@RequestParam String confirmPassword,HttpSession httpSession)
    {
        if(password.equals(confirmPassword))
        {
            if(userService.register(username,password))
            {
                httpSession.setAttribute("username",username);
                return response.toJSON(200,"注册成功");
            }
            else
            {
                return response.toJSON(400,"该用户已存在");
            }
        }
        else{
            return response.toJSON(400,"两次密码不匹配");
        }

    }

    /**
     * 检查登录状态
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "getUserInfo")
    @PermissionAnnotation()
    public String checkState(HttpSession httpSession)
    {
        return response.toJSON(200);
    }

    /**
     * 通过用户名获得用户信息
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "checkState")
    @PermissionAnnotation()
    public String getUserInfo(HttpSession httpSession)
    {
        return response.toJSON(200,userMapper.selectUserByUsername(httpSession.getAttribute("username").toString()));
    }

    /**
     * 注销
     * @param httpSession
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpSession httpSession)
    {
        httpSession.invalidate();
        return response.toJSON(200,"注销成功");
    }
}
