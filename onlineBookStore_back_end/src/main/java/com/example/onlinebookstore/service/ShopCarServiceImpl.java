package com.example.onlinebookstore.service;


import com.example.onlinebookstore.common.Response;
import com.example.onlinebookstore.dao.BookMapper;
import com.example.onlinebookstore.dao.ShopCarMapper;
import com.example.onlinebookstore.domain.Book;
import com.example.onlinebookstore.domain.ShopCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ShopCarServiceImpl implements ShopCarService {

    @Autowired
    BookMapper bookMapper;
    @Autowired
    Response response;
    @Autowired
    ShopCarMapper shopCarMapper;
    @Override
    public String addToShopCar(Integer goodsId, Integer num,String username) {
        Book nowBook = bookMapper.selectById(goodsId);
        if(nowBook==null)
        {
            return response.toJSON(403,"书籍不存在");
        }
        if(nowBook.getStore()<num)
        {
            return response.toJSON(403,"库存不足");
        }
        ShopCar shopCar=new ShopCar();
        shopCar.setIfDelete(0);
        shopCar.setGoodsId(goodsId);
        shopCar.setNum(num);
        shopCar.setPrice(nowBook.getPrice());
        shopCar.setName(nowBook.getName());
        shopCar.setUsername(username);
        System.out.println(shopCar.toString());
        shopCarMapper.addToShopCar(shopCar);
        return response.toJSON(200,"加入成功");
    }

    @Override
    public List<ShopCar> getList(Integer nowPage, Integer pageSize, String username) {
        int start=(nowPage-1)*pageSize;
        return shopCarMapper.getList(start,pageSize,username);
    }

    @Override
    public void removeFromShopCar(Integer[] list, String username) {
        for(int i=0;i<list.length;i++)
        {
            shopCarMapper.removeFromShopCar(list[i],username);
        }
    }
}
