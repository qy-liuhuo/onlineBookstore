package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.common.PermissionAnnotation;
import com.example.onlinebookstore.common.Response;
import com.example.onlinebookstore.service.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("order")
public class BookOrderController {
    @Autowired
    Response response;
    @Autowired
    BookOrderService bookOrderService;


    @RequestMapping("getOrderList")
    @PermissionAnnotation()
    public String getOrderList(HttpSession httpSession)
    {
        return  bookOrderService.getOrderList(httpSession.getAttribute("username").toString());
    }
}
