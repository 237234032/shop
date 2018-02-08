package com.mz.shop.database.utils;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
/**
 * @Title:
 * @Description:
 * @author: chris
 * @version: 1.0.0
 * @date: 2018/01/05 11:23
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
