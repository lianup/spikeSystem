package com.lianup.spikesystem.service.impl;

import com.lianup.spikesystem.dao.GoodsInfoDAO;
import com.lianup.spikesystem.service.MsgRevService;
import com.lianup.spikesystem.vo.MsgObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

@Component
public class MsgRevServiceImpl implements MsgRevService{

    @Autowired
    private GoodsInfoDAO goodsInfoDAO;

    /**
     * 还没处理接受不到消息的情况
     * @param message
     */
    @JmsListener(destination = "GOODS_QUEUE")
    @Transactional
    @Override
    public void receiveMsg(Map<String, Object> message) {
        Integer goodsId = (Integer) message.get("goodsId");
        Integer userId = (Integer) message.get("userId");
        goodsInfoDAO.insertSell(goodsId, userId);
        goodsInfoDAO.decCountById(goodsId);
    }

}
