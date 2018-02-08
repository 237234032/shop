package com.mz.shop.module.sys.web;

import com.mz.shop.module.sys.dto.LoginDTO;
import com.mz.shop.module.user.entity.TbUser;
import com.mz.shop.module.user.service.TbUserService;
import com.mz.shop.module.user.utils.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title:
 * @Description:
 * @author: chris
 * @version: 1.0.0
 * @date: 2018/01/08 17:35
 */
@Controller
public class LogoutController {

    /**
     * 注销用户
     * @return
     */
    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request) {
        LoginDTO principal = UserUtils.getPrincipal();
        if (principal != null) {
            // 交给 Shiro 操作
            SecurityUtils.getSubject().logout();
        }
        return "redirect:/login";
    }
}
