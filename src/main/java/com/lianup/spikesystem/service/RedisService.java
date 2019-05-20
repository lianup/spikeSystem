package com.lianup.spikesystem.service;


import org.springframework.stereotype.Service;

/**
 * 对缓存的操作
 * 库存放到缓存, mysql 的同步放到队列里面异步操作以减少时间
 */
//@Service
public interface RedisService {

    /**
     * 若库存的量大于0,则更新库存(库存量-1)
     * @param goodsId
     * @return 成功返回 true, 否则返回 false
     */
    boolean updateCount(int goodsId);


    /**
     * 往缓存中放商品和库存信息
     * @param goodsId
     * @param count
     * @return 成功时返回1,覆盖返回0,失败返回-1
     */

    long addGoodsCount(int goodsId, int count);


    /**
     * 获得锁
     * @param id
     * @return
     */
    boolean lock(int id, int entityType);

    /**
     * 解锁
     * @param id
     * @return
     */
    boolean unlock(int id, int entityType);
}
