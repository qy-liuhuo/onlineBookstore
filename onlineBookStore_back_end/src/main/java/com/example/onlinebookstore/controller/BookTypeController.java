package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.common.Response;
import com.example.onlinebookstore.dao.BookTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookType")
public class BookTypeController {
    @Autowired
    Response response;
    @Autowired
    BookTypeMapper bookTypeMapper;

    @RequestMapping("getAll")
    public String getAll()
    {
        return response.toJSON(200,bookTypeMapper.selectAllType());
    }
}
