package com.lianup.spikesystem.dao;

import com.lianup.spikesystem.dao.entity.GoodsInfo;
import org.apache.ibatis.annotations.*;

import javax.print.DocFlavor;

/**
 * 秒杀商品信息的数据库操作
 */
@Mapper
public interface GoodsInfoDAO {

    String INFO_TABLE_NAME = "goods_info";
    String SELL_TABLE_NAME = "goods_sold";
    /**
     * 增加商品
     * @param goodsInfo
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert({"INSERT INTO", INFO_TABLE_NAME, "(name,count,price) VALUES(#{name},#{count},#{price})"})
    int insertGoods(GoodsInfo goodsInfo);

    /**
     * 获得商品的剩余量
     * @param id
     */
    @Select({"SELECT count from", INFO_TABLE_NAME, "WHERE id = #{id}"})
    int selectCountById(int id);

    /**
     * 修改商品的剩余量
     * @param id
     */
    @Update({"UPDATE", INFO_TABLE_NAME, "SET count = #{count} WHERE id = #{id}"})
    void updateCountById(@Param("count") int count, @Param("id") int id);


    /**
     * 让 id 的商品数量-1
     * @param id
     */
    @Update({"UPDATE", INFO_TABLE_NAME, "SET count = count - 1 WHERE id = #{id}"})
    void decCountById(int id);

    /**
     * 增加订单
     * @param goodsId
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert({"INSERT INTO", SELL_TABLE_NAME, "(goods_id,user_id) VALUES(#{goodsId}, #{userId})"})
    Integer insertSell(@Param("goodsId") int goodsId, @Param("userId") int userId);



}
