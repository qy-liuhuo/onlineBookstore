package com.example.onlinebookstore.dao;

import com.example.onlinebookstore.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {
    List<Book> selectBook(String keyWord,int bookType);
    List<Book> selectBookByKey(String keyWord);
    List<Book> selectBookByType(int bookType);
    List<Book> selectAll();
    Book selectById(Integer id);
    Integer updateStore(Integer id);
}
