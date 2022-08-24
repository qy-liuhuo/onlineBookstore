package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.common.Response;
import com.example.onlinebookstore.domain.Book;
import com.example.onlinebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookListController {
    @Autowired
    Response response;

    @Autowired
    BookService bookService;

    @RequestMapping(value = "getBooks",method = RequestMethod.POST)
    public String getBooks(@RequestParam String keyWord,@RequestParam Integer type)
    {
        List<Book> bookList=bookService.selectBookList(keyWord,type);
        return response.toJSON(200,bookList);
    }

}
