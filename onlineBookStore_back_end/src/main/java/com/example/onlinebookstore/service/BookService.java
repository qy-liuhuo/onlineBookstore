package com.example.onlinebookstore.service;

import com.example.onlinebookstore.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> selectBookList(String keyWord,int type);
    Book selectBookById(Integer id);
}
