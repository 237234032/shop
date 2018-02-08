package com.mz.shop.module.sys.interceptor;

import com.mz.shop.common.utils.UserAgentUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title:
 * @Description:
 * @author: chris
 * @version: 1.0.0
 * @date: 2018/01/12 16:18
 */
public class MobileInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null){
            String viewName = modelAndView.getViewName();
            if(UserAgentUtils.isMobileOrTablet(httpServletRequest)&&!viewName.contains("redirect")){
                modelAndView.setViewName("mobile"+viewName.replace("module",""));
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
