package com.lianup.spikesystem.dao.entity;

import org.springframework.data.annotation.Id;

/**
 * 商品信息类
 */
public class GoodsInfo {

    @Id
    private int id;
    private String name;
    private int count;
    private int price;


    public GoodsInfo(String name, int count, int price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }



    public int getCount() {
        return count;
    }


    public int getPrice() {
        return price;
    }


}
