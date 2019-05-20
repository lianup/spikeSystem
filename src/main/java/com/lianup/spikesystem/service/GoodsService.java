package com.lianup.spikesystem.service;

import org.springframework.stereotype.Service;

/**
 * 对商品的操作
 */
//@Service
public interface GoodsService {

    /**
     * 增加订单
     * 错误返回-1
     */
    int addOrder(int goodsId, int userId);

}
