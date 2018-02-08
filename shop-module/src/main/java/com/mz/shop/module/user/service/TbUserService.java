package com.mz.shop.module.user.service;

import com.mz.shop.module.sys.dto.LoginDTO;
import com.mz.shop.module.user.entity.TbUser;

import java.util.List;

/**
 * @Title:
 * @Description:
 * @author: chris
 * @version: 1.0.0
 * @date: 2018/01/06 17:09
 */
public interface TbUserService {
    // 登录
    public TbUser login(LoginDTO loginDTO);

    // 根据id查找用户
    public TbUser selectById(Long id);

    // 查找全部用户
    public List<TbUser> selectAll();

    // 操作用户信息
    public void save(TbUser tbUser);

    // 根据id删除用户
    public void delete(Long id);

    // 验证表单提交数据唯一性
    public boolean checkOnly(TbUser tbUser);

    public TbUser getByLoginId(String loginID);
}
