package com.mz.shop.module.user.test;

import com.mz.shop.module.user.entity.TbUser;
import com.mz.shop.module.user.mapper.TbUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @Title:
 * @Description:
 * @author: chris
 * @version: 1.0.0
 * @date: 2018/01/05 19:10
 */
@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration({"classpath:spring-context.xml"})
public class UserMapperTest {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Test
    public void insertUser(){
        TbUser tbUser = new TbUser();
        tbUser.setId(1L);
        tbUser.setUsername("root");
        tbUser.setPassword("111111");
        tbUser.setEmail("root123@mz.com");
        tbUser.setPhone("1376668888");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUserMapper.insert(tbUser);
    }

    @Test
    public void getById(){
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(1L);
        System.out.println(tbUser.getUsername());
    }
}
