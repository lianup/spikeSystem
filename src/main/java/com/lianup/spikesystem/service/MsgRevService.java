package com.lianup.spikesystem.service;

import java.util.Map;

/**
 * 消息队列消费者,负责把队列中的订单记录更新到数据库
 */
public interface MsgRevService {

    /**
     * 把订单消息添加到 mysql,同时减少 mysql 库存
     * @return
     */
    void receiveMsg(Map<String, Object> message);
}
