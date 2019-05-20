package com.lianup.spikesystem.dao;

import com.lianup.spikesystem.dao.entity.GoodsInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDAO {
    @Autowired
    GoodsInfoDAO goodsInfoDAO;
    @Autowired
    UserInfoDAO userInfoDAO;

//    @Test
//    public void testGoods() {
//        GoodsInfo goods1 = new GoodsInfo("小米手机2", 30,999);
//        int id = goodsInfoDAO.insertGoods(goods1);
//        int count = goodsInfoDAO.selectCountById(id);
//        goodsInfoDAO.updateCountById(10,id);
//        int sellId = goodsInfoDAO.insertSellById(1);
//        System.out.println(id + " " + count);
//
//    }

//    @Test
//    public void testUser(){
////        int id = userInfoDAO.insert("lianup6", "123456");
////        assert(id != -1);
//        Integer get = userInfoDAO.login("lianup2", "123456");
//        assert(get != null);
//    }
}
