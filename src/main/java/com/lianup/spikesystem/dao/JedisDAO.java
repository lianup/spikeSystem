package com.lianup.spikesystem.dao;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 对 Redis 的操作
 */
@Component
@PropertySource(value = {"classpath:application.properties"},encoding="utf-8")
public class JedisDAO implements InitializingBean {


    @Value("${redisUrl}")
    private String redisUrl;

    private JedisPool jedisPool;

    @Override
    public void afterPropertiesSet() {
        jedisPool = new JedisPool(redisUrl);
    }


    private Jedis getConnection()
    {
        Jedis jedis;
        jedis = jedisPool.getResource();
        return jedis;
    }



    /**
     * 以前用来做分布式锁的旧方法
     * @param key
     * @return
     */
    public long setnx(String key){
        Jedis jedis = getConnection();
        long res = jedis.setnx(key, key);
        jedis.close();
        return res;
    }

    public long sadd(String key, String ... member){
        Jedis jedis = getConnection();
        long res = jedis.sadd(key, member);
        jedis.close();
        return res;
    }

    public boolean sismember(String key, String member){
        Jedis jedis = getConnection();
        boolean res = jedis.sismember(key, member);
        jedis.close();
        return res;
    }


    public List<String> lrange(String key, int offset, int num){
        Jedis jedis = getConnection();
        List<String> result = jedis.lrange(key, offset, num);
        jedis.close();
        return result;
    }

    public List<Object> getTx(Transaction tx){
        Jedis jedis = getConnection();
        List<Object> list = tx.exec();
        try {
            tx.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Transaction multi(){
        Jedis jedis = getConnection();
        redis.clients.jedis.Transaction tx = jedis.multi();
        jedis.close();
        return tx;
    }

    public Double zscore(String key, String member){
        Jedis jedis = getConnection();
        Double score = jedis.zscore(key, member);
        jedis.close();
        return score;
    }

    public Long zcard(String key){
        Jedis jedis = getConnection();
        Long result = jedis.zcard(key);
        jedis.close();
        return result;
    }

    public List<Integer> zrevrange(String key, int start, int end){
        Jedis jedis = getConnection();
        Set<String> set = jedis.zrevrange(key, start, end);
        List<Integer> ids = new ArrayList<>();
        for (String entityId:
                set) {
            ids.add(Integer.valueOf(entityId));
        }
        jedis.close();
        return ids;

    }


    public void zremove(String key, String ... member){
        Jedis jedis = getConnection();
        jedis.zrem(key, member);
        jedis.close();
    }

    public void zadd(String key, Double time, String memeber){
        Jedis jedis = getConnection();
        jedis.zadd(key, time, memeber);
        jedis.close();
    }


    public long hsetnx(String key, String field, String val){
        Jedis jedis = getConnection();
        long res = jedis.hsetnx(key, field, val);
        jedis.close();
        return res;
    }

    public long hget(String key, String field){
        Jedis jedis = getConnection();
        String canDec = jedis.hget(key, field);
        return Long.valueOf(canDec);
    }

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment 。
     * @param key
     * @param field
     * @param increment
     * @return
     */
    public long hincrby(String key, String field, int increment){
        Jedis jedis = getConnection();
        long res = jedis.hincrBy(key, field, increment);
        jedis.close();
        return res;
    }



    public List<String> brpop(int timeout, String key){
        Jedis jedis = getConnection();
        List<String> list;
        list = jedis.brpop(timeout, key);
        jedis.close();
        return list;
    }

    public void lpush(String key, String member){
        Jedis jedis = getConnection();
        jedis.lpush(key, member);
        jedis.close();
    }

    public void srem(String key, String member){
        Jedis jedis = getConnection();
        jedis.srem(key, member);
        jedis.close();
    }

    public Long scard(String key){
        Jedis jedis = getConnection();
        Long count = jedis.scard(key);
        jedis.close();
        return count;
    }


    public String get(String key)
    {
        Jedis jedis = getConnection();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    public void del(String key)
    {
        Jedis jedis = getConnection();
        jedis.del(key);
        jedis.close();
    }

    public void setex(String key, int time, String value)
    {
        Jedis jedis = getConnection();
        jedis.setex(key, time, value);
        jedis.close();
    }

    public void set(String key, String value)
    {
        Jedis jedis = getConnection();
        jedis.set(key, value);
        jedis.close();
    }

    public void setBit(String key, int offset, boolean val){
        Jedis jedis = getConnection();
        jedis.setbit(key, offset, val);
        jedis.close();
    }

    public boolean getBit(String key, int offset){
        Jedis jedis = getConnection();
        boolean res = jedis.getbit(key, offset);
        jedis.close();
        return res;
    }

    /**
     * 获得连接之后又关闭,会不会有问题?
     * @param key
     */
    public void watch(String ... key ){
        Jedis jedis = getConnection();
        jedis.watch(key);
        jedis.close();
    }

    public void unwatch(){
        Jedis jedis = getConnection();
        jedis.unwatch();
        jedis.close();
    }

    public void flushAll(){
        Jedis jedis = getConnection();
        jedis.flushAll();
        jedis.close();
    }


}
