package com.example.onlinebookstore.common;

import java.util.Arrays;

public class OrderList {
    public  Integer[] idList;
    public Integer[] list;
    public Integer[] num;

    @Override
    public String toString() {
        return "OrderList{" +
                "list=" + Arrays.toString(list) +
                ", num=" + Arrays.toString(num) +
                '}';
    }
}
