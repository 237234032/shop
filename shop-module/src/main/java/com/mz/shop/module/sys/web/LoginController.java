package com.mz.shop.module.sys.web;

import com.mz.shop.common.utils.CookieUtils;
import com.mz.shop.common.utils.IDUtils;
import com.mz.shop.module.sys.dto.LoginDTO;
import com.mz.shop.module.user.entity.TbUser;
import com.mz.shop.module.user.service.TbUserService;
import com.mz.shop.module.user.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Security;

/**
 * @Title:
 * @Description:
 * @author: chris
 * @version: 1.0.0
 * @date: 2018/01/08 15:09
 */
@Controller
public class LoginController {
    /**
     * 跳转登录页
     * @return
     */
    @RequestMapping(value = {"","login"},method =RequestMethod.GET)
    public String login(){
       LoginDTO loginDTO = UserUtils.getPrincipal();

       if(loginDTO != null){
            return "redirect:/main";
       }
       return "module/sys/login";
    }

    /**
     * 登录失败的处理，真正的登录 POST 请求由 Filter 完成
     * @param request
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginFail(HttpServletRequest request) {
        // 验证失败清空验证码
        request.getSession().setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, IDUtils.genId());
        return "module/sys/login";
    }
}
