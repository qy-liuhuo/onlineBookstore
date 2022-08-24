package com.example.onlinebookstore.service;

import org.springframework.stereotype.Service;

import java.util.List;


public interface BookOrderService {
    void generateOrder(String username,Integer[] list,Integer [] num);
    String getOrderList(String username);
}
