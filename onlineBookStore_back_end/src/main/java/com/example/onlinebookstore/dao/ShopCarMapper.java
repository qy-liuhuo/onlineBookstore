package com.example.onlinebookstore.dao;


import com.example.onlinebookstore.domain.ShopCar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShopCarMapper {
    Integer addToShopCar(ShopCar shopCar);
    Integer countNum(String username);
    List<ShopCar> getList(Integer begin,Integer size,String username);
    void removeFromShopCar(Integer id,String username);
}
