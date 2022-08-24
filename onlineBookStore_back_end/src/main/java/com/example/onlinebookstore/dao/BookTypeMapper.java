package com.example.onlinebookstore.dao;

import com.example.onlinebookstore.domain.BookType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookTypeMapper {
    List<BookType> selectAllType();
}
