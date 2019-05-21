package com.lianup.spikesystem.service.impl;

import com.lianup.spikesystem.common.EntityType;
import com.lianup.spikesystem.common.RedisKeyType;
import com.lianup.spikesystem.dao.JedisDAO;
import com.lianup.spikesystem.service.RedisService;
import com.lianup.spikesystem.service.UserService;
import com.lianup.spikesystem.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    JedisDAO jedisDAO;
    @Autowired
    RedisService redisService;


    private static final String POST_SET_NAME = "POST:SET";



    public boolean delPost(int userId){
        if(userId < 0){
            return false;
        }
        jedisDAO.srem(POST_SET_NAME, Integer.toString(userId));
        return true;
    }

    /**
     * 如何解决 set 中修改的并发问题:lock
     * @param id
     * @return
     */
    @Override
    public boolean canPost(int id) {
        if(id <= 0){
            return false;
        }
        boolean res = true;
            long get = jedisDAO.sadd(POST_SET_NAME, Integer.toString(id));
            // 域不存在,设置成功
            if(get == 0){
                res = false;
            }
            return res;
        }
    }
