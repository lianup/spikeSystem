package com.lianup.spikesystem.service.impl;

import com.lianup.spikesystem.dao.GoodsInfoDAO;
import com.lianup.spikesystem.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodServiceImpl implements GoodsService{

    @Autowired
    GoodsInfoDAO goodsInfoDAO;


    @Override
    public int addOrder(int goodsId, int userId) {
        if(goodsId < 0 || userId < 0){
            return -1;
        }
        goodsInfoDAO.insertSell(goodsId, userId);
        return 1;
    }

}
