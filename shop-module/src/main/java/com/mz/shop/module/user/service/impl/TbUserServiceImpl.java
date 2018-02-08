package com.mz.shop.module.user.service.impl;

import com.mz.shop.common.utils.IDUtils;
import com.mz.shop.module.sys.dto.LoginDTO;
import com.mz.shop.module.user.entity.TbUser;
import com.mz.shop.module.user.mapper.TbUserMapper;
import com.mz.shop.module.user.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Title:
 * @Description:
 * @author: chris
 * @version: 1.0.0
 * @date: 2018/01/06 17:12
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    /**
     * 根据用户名/邮箱/手机号和密码登录
     * @param loginDTO
     */
    @Override
    public TbUser login(LoginDTO loginDTO) {
        TbUser result = null;

        // 封装查询条件
        Example example = new Example(TbUser.class);
        example.createCriteria().andEqualTo("username",loginDTO.getLoginID())
                                .orEqualTo("email",loginDTO.getLoginID())
                                .orEqualTo("phone",loginDTO.getLoginID());

        // 根据条件查询
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);

        // 判断查询结果,账号密码正确
        if(tbUsers!=null&&tbUsers.size()==1){
            result = tbUsers.get(0);
            if(result.getPassword().equals(DigestUtils.md5DigestAsHex(loginDTO.getLoginPWD().getBytes()))){
                return result;
            }
        }

        // 账号密码错误
        else {

        }
        return null;
    }

    /**
     * 根据ID查找用户
     * @param id
     */
    @Override
    public TbUser selectById(Long id) {
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(id);
        return tbUser;
    }

    /**
     * 获取全部用户
     */
    @Override
    public List<TbUser> selectAll() {
        List<TbUser> tbUsers = tbUserMapper.selectAll();
        return tbUsers;
    }

    /**
     * 新增或编辑用户信息
     * @param tbUser
     */
    @Override
    public void save(TbUser tbUser) {

        // 如果id不为空，编辑
        if(tbUser.getId() != null){
            if(StringUtils.isBlank(tbUser.getPassword())){
                tbUser.setPassword(null);
            }
            else {
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            }
            tbUserMapper.updateByPrimaryKeySelective(tbUser);
        }
        // 新增
        else {
            tbUser.setId(IDUtils.genId());
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            tbUser.setCreated(new Date());
            tbUser.setUpdated(new Date());
            tbUserMapper.insert(tbUser);
        }
    }

    /**
     * 根据id删除用户
     * @param id
     */
    @Override
    public void delete(Long id) {
        tbUserMapper.deleteByPrimaryKey(id);
    }

    /**
     * 验证表单数据是否唯一
     * @param tbUser
     */
    @Override
    public boolean checkOnly(TbUser tbUser) {
        if(StringUtils.isNotBlank(tbUser.getUsername())){
            return checkUsername(tbUser.getUsername());
        }
        else if (StringUtils.isNotBlank(tbUser.getPhone())){
            return checkPhone(tbUser.getPhone());
        }
        else if(StringUtils.isNotBlank(tbUser.getEmail())){
            return checkEmail(tbUser.getEmail());
        }
        return true;
    }

    private boolean checkUsername(String username) {
        if(getTbUsers("username",username).size()>0){
            return false;
        }
        return true;
    }
    private boolean checkPhone(String phone) {
        if(getTbUsers("phone",phone).size()>0){
            return false;
        }
        return true;
    }
    private boolean checkEmail(String email) {
        if(getTbUsers("email",email).size()>0){
            return false;
        }
        return true;
    }

    /**
     * 根据filed查询笔数
     * @param property
     * @param field
     */
    private List<TbUser> getTbUsers(String property,String field) {
        Example example = new Example(TbUser.class);
        example.createCriteria().andEqualTo(property,field);
        return tbUserMapper.selectByExample(example);
    }

    @Override
    public TbUser getByLoginId(String loginID) {
        TbUser tbUser = null;

        // 封装查询条件
        Example example = new Example(TbUser.class);
        example.createCriteria()
                .andEqualTo("email", loginID)
                .orEqualTo("username", loginID)
                .orEqualTo("phone", loginID);

        // 根据条件查询
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);

        // 直接获取用户
        if (tbUsers != null && tbUsers.size() == 1) {
            tbUser = tbUsers.get(0);
        }

        return tbUser;
    }
}
