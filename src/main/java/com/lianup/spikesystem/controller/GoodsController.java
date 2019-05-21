package com.lianup.spikesystem.controller;

import com.lianup.spikesystem.common.ResponseCode;
import com.lianup.spikesystem.service.MsgSendService;
import com.lianup.spikesystem.service.RedisService;
import com.lianup.spikesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@RequestMapping(value = "/spike")
@org.springframework.stereotype.Controller
public class GoodsController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private MsgSendService msgSendService;
    @Autowired
    private UserService userService;

    /**
     * 秒杀接口
     * 本来 userId 应该是用户登录后在session获取的,但是现在没写登录这个东西
     */
    @ResponseBody
    @RequestMapping(value = "/goods/skill")
    public Map<String, Object> skill(@RequestParam int goodsId, @RequestParam int userId){
        Map<String, Object> retMap = new HashMap<>(1);
        if(goodsId < 0 || userId < 0){
            retMap.put("code", ResponseCode.SERVER_ERROR);
        }else {
            // 查看该用户是否能请求秒杀
            boolean res = userService.canPost(userId);
            if(!res){
                retMap.put("code", ResponseCode.OTHER_ERROR);
                return retMap;
            }else {
                    // 更新缓存中的库存数量
                    if(!redisService.updateCount(goodsId)){
                        retMap.put("code", ResponseCode.OTHER_ERROR);
                        userService.delPost(userId);
                        return retMap;
                    }else {
                        // 更新库存成功,向消息队列中发送消息 (还没有考虑发送失败)
                        Map<String, Object> msg = new HashMap<>(2);
                        msg.put("goodsId", goodsId);
                        msg.put("userId", userId);
                        msgSendService.sendMessage(msg);
                        retMap.put("code", ResponseCode.OK);
                    }
                }
            }
            return retMap;
        }


}
