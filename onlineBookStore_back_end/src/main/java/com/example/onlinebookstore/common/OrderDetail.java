package com.example.onlinebookstore.common;

import com.example.onlinebookstore.dao.BookMapper;
import com.example.onlinebookstore.domain.Book;
import com.example.onlinebookstore.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class OrderDetail {

    public List<Integer> getBookId() {
        return bookId;
    }

    public List<Integer> getNum() {
        return num;
    }

    public void setBookId(List<Integer> bookId) {
        this.bookId = bookId;
    }

    public void setNum(List<Integer> num) {
        this.num = num;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    private List<Integer> bookId;
    private List<Integer> num;
    private Double total;
    public OrderDetail()
    {
        bookId=new ArrayList<Integer>();
        num=new ArrayList<Integer>();
        this.total=0.0;
    }
    public void push(Integer id,Integer n,Double cost)
    {
        bookId.add(id);
        num.add(n);
        total=total+cost;
    }

    public Double getTotal() {
        return total;
    }

}
