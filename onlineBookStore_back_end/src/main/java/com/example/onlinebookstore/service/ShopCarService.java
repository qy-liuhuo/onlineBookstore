package com.example.onlinebookstore.service;

import com.example.onlinebookstore.domain.ShopCar;

import java.util.List;

public interface ShopCarService {
    String addToShopCar(Integer goodsId,Integer num,String username);
    List<ShopCar> getList(Integer nowPage,Integer pageSize,String username);
    void removeFromShopCar(Integer [] list,String username);
}
