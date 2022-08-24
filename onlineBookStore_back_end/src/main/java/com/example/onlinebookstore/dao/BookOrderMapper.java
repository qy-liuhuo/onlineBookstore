package com.example.onlinebookstore.dao;


import com.example.onlinebookstore.domain.BookOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookOrderMapper {
    void saveOrder(BookOrder bookOrder);
    List<BookOrder> selectBookOrder(String username);
}
