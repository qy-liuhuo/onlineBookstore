package com.example.onlinebookstore.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.onlinebookstore.common.OrderDetail;
import com.example.onlinebookstore.common.Response;
import com.example.onlinebookstore.dao.BookMapper;
import com.example.onlinebookstore.dao.BookOrderMapper;
import com.example.onlinebookstore.domain.Book;
import com.example.onlinebookstore.domain.BookOrder;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookOrderServiceImpl implements BookOrderService{
    @Autowired
    BookMapper bookMapper;
    @Autowired
    BookOrderMapper bookOrderMapper;
    @Autowired
    Response response;
    @Override
    public void generateOrder( String username, Integer[] list, Integer[] num) {
        OrderDetail orderDetail=new OrderDetail();
        for(int i=0;i<list.length;i++)
        {
            Book tempBook=bookMapper.selectById(list[i]);
            if(tempBook.getStore()<num[i])
            {
                continue;
            }
            else
            {
                bookMapper.updateStore(list[i]);
                System.out.println(list[i]);
                System.out.println(num[i]);
                orderDetail.push(list[i],num[i],tempBook.getPrice()*num[i]);
            }
        }
        BookOrder bookOrder=new BookOrder();
        bookOrder.setDetail(JSON.toJSONString(orderDetail));
        bookOrder.setUser(username);
        bookOrder.setTime(new Date());
        bookOrder.setTotal(orderDetail.getTotal());
        bookOrder.setState("待发货");
        bookOrderMapper.saveOrder(bookOrder);
    }

    @Override
    public String getOrderList(String username) {
        List<BookOrder> orders= bookOrderMapper.selectBookOrder(username);
        for(int i=0;i<orders.size();i++)
        {
            String description="";
            OrderDetail temp=JSON.parseObject(orders.get(i).getDetail(),OrderDetail.class);
            List<Integer> bookId=temp.getBookId();
            List<Integer> num=temp.getNum();
            for(int j=0;j<bookId.size();j++){
                description=description+bookMapper.selectById(bookId.get(j)).getName()+"*"+num.get(j).toString()+"\n";
            }
            orders.get(i).setDescription(description);
        }
        return response.toJSON(200,orders);
    }
}
