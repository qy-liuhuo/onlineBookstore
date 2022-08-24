package com.example.onlinebookstore.controller;


import com.example.onlinebookstore.common.OrderList;
import com.example.onlinebookstore.common.PermissionAnnotation;
import com.example.onlinebookstore.common.Response;
import com.example.onlinebookstore.dao.ShopCarMapper;
import com.example.onlinebookstore.service.BookOrderService;
import com.example.onlinebookstore.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("shopCar")
public class ShopCarController {
    @Autowired
    ShopCarService shopCarServce;
    @Autowired
    ShopCarMapper shopCarMapper;
    @Autowired
    Response response;
    @Autowired
    BookOrderService bookOrderService;

    @RequestMapping(value = "addToShopCar",method = RequestMethod.POST)
    @PermissionAnnotation()
    public String addToShop(@RequestParam Integer goodsId, @RequestParam Integer num, HttpSession httpSession)
    {
        System.out.println(httpSession.getAttribute("username").toString());
        return shopCarServce.addToShopCar(goodsId,num,httpSession.getAttribute("username").toString());
    }
    @RequestMapping(value="countNum",method = RequestMethod.GET)
    @PermissionAnnotation()
    public String countNum(HttpSession httpSession)
    {
        return response.toJSON(200,shopCarMapper.countNum(httpSession.getAttribute("username").toString()));
    }
    @RequestMapping(value="getList",method = RequestMethod.POST)
    @PermissionAnnotation()
    public String getList(@RequestParam Integer nowPage,@RequestParam Integer pageSize,HttpSession httpSession)
    {
        return response.toJSON(200,shopCarServce.getList(nowPage,pageSize,httpSession.getAttribute("username").toString()));
    }
    @RequestMapping(value="buy")
    @PermissionAnnotation()
    //public String buy(@RequestParam(value = "num[]") Integer[] num,@RequestParam(value = "list[]") Integer[] list,HttpSession httpSession)
    public String buy(@RequestBody OrderList orderList, HttpSession httpSession)
    {
        //将购物车列表移除
        shopCarServce.removeFromShopCar(orderList.idList,httpSession.getAttribute("username").toString());
        //购买
        bookOrderService.generateOrder(httpSession.getAttribute("username").toString(),orderList.list,orderList.num);
        return response.toJSON(200,"购买成功");
    }
}
