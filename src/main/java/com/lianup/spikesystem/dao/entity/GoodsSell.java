package com.lianup.spikesystem.dao.entity;

import org.springframework.data.annotation.Id;

/**
 * 商品的订单信息
 */
public class GoodsSell {
    @Id
    private int id;
    private int goodsId;
    private int userId;

    public int getId() {
        return id;
    }

    public int getGoodsId() {
        return goodsId;
    }


}
