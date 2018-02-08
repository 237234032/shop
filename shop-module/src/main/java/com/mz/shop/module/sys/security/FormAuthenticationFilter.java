package com.mz.shop.module.sys.security;

import com.mz.shop.common.utils.StringUtils;
import com.mz.shop.common.utils.UserAgentUtils;
import com.mz.shop.module.sys.dto.LoginDTO;
import com.mz.shop.module.user.utils.UserUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 自定义表单验证过滤类（含验证码）
 * @Title:
 * @Description:
 * @author: chris
 * @version: 1.0.0
 * @date: 2018/01/15 20:03
 */
@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {
    /**
     * 创建令牌
     ** @param request
     * @param response
     * @return
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = WebUtils.getCleanParam(request, "loginID");
        String loginPWD = WebUtils.getCleanParam(request, "loginPWD");
        if(loginPWD==null){
            loginPWD = "0";
        }
        String password = DigestUtils.md5DigestAsHex(loginPWD.getBytes());
        boolean rememberMe = "on".equals(WebUtils.getCleanParam(request, "remember"));
        String host = StringUtils.getRemoteAddress((HttpServletRequest) request);
        String validateCode = WebUtils.getCleanParam(request, "validateCode");
        return new UsernamePasswordToken(username, password, rememberMe, host, validateCode);
    }

    /**
     * 登录成功后跳转
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        LoginDTO principal = UserUtils.getPrincipal();
        if (principal != null) {
            WebUtils.issueRedirect(request, response, "/main", null, true);
        }
    }

    /**
     * 登录失败调用事件
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        String className = e.getClass().getName();
        // 将错误信息放入 request 中，供前端显示
        if (IncorrectCredentialsException.class.getName().equals(className) || UnknownAccountException.class.getName().equals(className)) {
            request.setAttribute("message", "用户名或密码错误，请重试");
        }
        else if (e.getMessage() != null && StringUtils.startsWith(e.getMessage(), "msg:")) {
            request.setAttribute("message", e.getMessage().replace("msg:", ""));
        }
        return true;
    }
}
