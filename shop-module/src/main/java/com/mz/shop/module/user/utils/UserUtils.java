package com.mz.shop.module.user.utils;

import com.mz.shop.module.sys.dto.LoginDTO;
import com.mz.shop.module.user.entity.TbUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpSession;

/**
 * 用户操作工具类
 * @Title:
 * @Description:
 * @author: chris
 * @version: 1.0.0
 * @date: 2018/01/15 22:30
 */
public class UserUtils {
    /**
     * 从会话获取用户信息
     * @param session
     * @return
     */
    public static TbUser getUser(HttpSession session) {
        return (TbUser) session.getAttribute("user");
    }

    /**
     * 获取当前登录对象
     *
     * @return
     */
    public static LoginDTO getPrincipal() {
        Subject subject = SecurityUtils.getSubject();
        LoginDTO loginDTO = (LoginDTO) subject.getPrincipal();
        if (loginDTO != null) {
            return loginDTO;
        }
        return null;
    }
}
