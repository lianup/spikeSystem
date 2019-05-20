package com.lianup.spikesystem.vo;

import java.io.Serializable;

/**
 * 发送的消息--goosId 和 userId
 */
public class MsgObject implements Serializable{

    private int goodsId;
    private int userId;

    public MsgObject(int goodsId, int userId) {
        this.goodsId = goodsId;
        this.userId = userId;
    }


    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MsgObject{" +
                "goodsId=" + goodsId +
                ", userId=" + userId +
                '}';
    }
}
