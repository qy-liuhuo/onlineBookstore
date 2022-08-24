package com.example.onlinebookstore.service;

import com.example.onlinebookstore.dao.BookMapper;
import com.example.onlinebookstore.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> selectBookList(String keyWord, int type) {
        if(keyWord.equals("") && type==-1)
        {
            return bookMapper.selectAll();
        }
        else if(!keyWord.equals("") &&type!=-1)
        {
            return bookMapper.selectBook(keyWord,type);
        }
        else if(!keyWord.equals(""))
        {
            System.out.println(keyWord);
            return bookMapper.selectBookByKey(keyWord);
        }
        else{
            return bookMapper.selectBookByType(type);
        }
    }

    @Override
    public Book selectBookById(Integer id) {
        return bookMapper.selectById(id);
    }
}
