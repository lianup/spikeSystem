package com.lianup.spikesystem.util;

import com.lianup.spikesystem.common.RedisKeyType;

/**
 * 生成 RedisKey 的帮助类
 */
public class RedisKeyUtil {

    private static final String SPILT = ":";
    private static final String TOKEN = "TOKEN";
    private static final String LOCK = "LOCK";


    public static String get(int entityId, int entityType, int keyType){
        switch (keyType){
            case RedisKeyType.TOKEN:
                return getKey(TOKEN, entityId, entityType);
            case RedisKeyType.LOCK:
                return getKey(LOCK, entityId, entityType);
            default:
                return null;
        }
    }

    private static String getKey(String keyTypeName){
        StringBuilder builder = new StringBuilder();
        return builder
                .append(keyTypeName)
                .toString();

    }

    private static String getKey(String keyTypeName,int entityId, int entityType){
        StringBuilder builder = new StringBuilder();
        return builder
                .append(keyTypeName)
                .append(SPILT)
                .append(entityType)
                .append(SPILT)
                .append(entityId).toString();
    }

}
