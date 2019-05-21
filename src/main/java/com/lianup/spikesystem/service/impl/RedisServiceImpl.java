package com.lianup.spikesystem.service.impl;


import com.lianup.spikesystem.common.EntityType;
import com.lianup.spikesystem.common.RedisKeyType;
import com.lianup.spikesystem.dao.JedisDAO;
import com.lianup.spikesystem.service.RedisService;
import com.lianup.spikesystem.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    JedisDAO jedisDAO;

    // 库存表
    private static final String HASH_TABLE_NAME = "STOCK:HASHTABLE";


    @Override
    public boolean updateCount(int goodsId) {
//        if(goodsId < 0){
//            return false;
//        }
//        if(!lock(goodsId, EntityType.GOODS)){
//            return false;
//        }
        // 若库存大于0,则更新
        if(jedisDAO.hincrby(HASH_TABLE_NAME, Integer.toString(goodsId), -1) >= 0){
            return true;
        }
        return false;
    }

    @Override
    public long addGoodsCount(int goodsId, int count) {
        if(goodsId < 0 || count <= 0){
            return -1;
        }
        long res = jedisDAO.hsetnx(HASH_TABLE_NAME, String.valueOf(goodsId), String.valueOf(count));
        return res;
    }


    @Override
    public boolean lock(int id, int entityType) {
        String lockName = RedisKeyUtil.get(id, entityType, RedisKeyType.LOCK);
        long cur = System.currentTimeMillis();
        // 并发时卡在这个地方了,说明非常不合理
        int count = 10;
        boolean res;
        while(!(res = jedisDAO.setLock(lockName, Integer.toString(id), 60)) && count > 0){
            count--;
        }
        return res;

    }

    @Override
    public boolean unlock(int id, int entityType) {
        String lockName = RedisKeyUtil.get(id, entityType, RedisKeyType.LOCK);
        jedisDAO.del(lockName);
        return true;
    }
}
