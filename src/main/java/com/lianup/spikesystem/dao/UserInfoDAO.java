package com.lianup.spikesystem.dao;

import com.lianup.spikesystem.dao.entity.GoodsInfo;
import com.lianup.spikesystem.dao.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * 用户信息的数据库操作
 */
@Mapper
public interface UserInfoDAO {

    String INFO_TABLE_NAME = "user_info";

    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    @Insert({"INSERT INTO", INFO_TABLE_NAME, "(name,password) VALUES(#{name}, #{password})"})
    int insert(@Param("name")String name, @Param("password") String password);

    /**
     * 用户登录
     */
    @Select({"SELECT user_id FROM", INFO_TABLE_NAME, "WHERE name = #{name} AND password = #{password}"})
    Integer login(@Param("name")String name, @Param("password") String password);

}
