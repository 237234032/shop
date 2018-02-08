package com.mz.shop.module.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Title:
 * @Description:
 * @author: chris
 * @version: 1.0.0
 * @date: 2018/01/08 16:55
 */
@Controller
public class MainController {

    // 跳转首页
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main() {
        return "module/sys/index";
    }
}
