package com.example.onlinebookstore.domain;

public class ShopCar {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIfDelete() {
        return ifDelete;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    private Integer id;
    private String name;
    private Integer goodsId;
    private Double price;
    private String username;
    private Integer ifDelete;
    private Integer num;

    @Override
    public String toString() {
        return "ShopCar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goodsId=" + goodsId +
                ", price=" + price +
                ", username='" + username + '\'' +
                ", delete=" + ifDelete +
                ", num=" + num +
                '}';
    }
}
