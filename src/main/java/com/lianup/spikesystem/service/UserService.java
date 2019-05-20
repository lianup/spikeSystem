package com.lianup.spikesystem.service;

import org.springframework.stereotype.Service;

/**
 * 前提:用户已登录,且用户登录的信息放在 redis 里面
 * 用户信息的验证,账号请求数量的验证
 */
//@Service
public interface UserService {

    /**
     * 验证此账号是否允许发出秒杀请求
     * 若此账号已请求,则不允许,否则可以发出请求
     */
    boolean canPost(int id);

}
