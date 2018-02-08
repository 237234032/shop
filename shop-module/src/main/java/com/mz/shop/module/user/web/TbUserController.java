package com.mz.shop.module.user.web;

import com.mz.shop.common.web.BaseController;
import com.mz.shop.module.user.entity.TbUser;
import com.mz.shop.module.user.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * JSR303 Validator(Hibernate Validator)工具类.
 * <p>
 * ConstraintViolation中包含propertyPath, message 和invalidValue等信息.
 * 提供了各种convert方法，适合不同的i18n需求:
 * 1. List<String>, String内容为message
 * 2. List<String>, String内容为propertyPath + separator + message
 * 3. Map<propertyPath, message>
 * <p>
 * 详情见wiki: https://github.com/springside/springside4/wiki/HibernateValidator
 * <p>Title: BeanValidators</p>
 * <p>Description: </p>
 * @author: chris
 * @version: 1.0.0
 * @date: 2018/01/09 14:45
 */
@Controller
@RequestMapping(value = "user")
public class TbUserController extends BaseController {

    // 自动注入
    @Autowired
    private TbUserService tbUserService;

    /**
     *  暴露表单引用对象为模型数据：放在处理器的一般方法（非功能处理方法）上时，
     *  是为表单准备要展示的表单引用对象，如注册时需要选择的所在城市等，
     *  而且在执行功能处理方法（@RequestMapping 注解的方法）之前，
     *  自动添加到模型对象中，用于视图页面展示时使用
     * @param id
     */
    @ModelAttribute
    public TbUser select(@RequestParam(required = false) Long id){
        TbUser tbUser = null;
        if(id != null){
            tbUser = tbUserService.selectById(id);
        }
        else{
            tbUser = new TbUser();
        }
        return tbUser;
    }

    /**
     * 跳转用户表单页
     */
    @RequiresPermissions(value = "module:user:form")
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){

        return "/module/user/form";
    }

    /**
     * 跳转用户列表页
     * @param model
     */
    @RequiresPermissions("module:user:list")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUsers",tbUsers);
        return "/module/user/list";
    }

    /**
     * 用户表单提交，有id编辑用户/无id新增用户
     * @param tbUser
     * @param model
     * @param redirectAttributese
     */
    @RequiresPermissions("module:user:save")
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public String list(TbUser tbUser, Model model, RedirectAttributes redirectAttributese){
        if(!beanValidator(model, tbUser)){
            return form();
        }
        tbUserService.save(tbUser);
        redirectAttributese.addFlashAttribute("message","保存成功。");
        return "redirect:/user/list";
    }

    /**
     * 根据id删除用户
     * @param id
     * @param redirectAttributes
     */
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public String delete(Long id, RedirectAttributes redirectAttributes){
        tbUserService.delete(id);
        redirectAttributes.addFlashAttribute("message","删除成功。");
        return "redirect:/user/list";
    }

    /**
     *
     * @param redirectAttributes
     * @param request
     *
     */
    @RequestMapping(value = "all",method = RequestMethod.POST)
    public String deleteAll(RedirectAttributes redirectAttributes,HttpServletRequest request){
        String[] uids = request.getParameterValues("uid");
        if(uids.length>0){
            for (String inp : uids) {
                tbUserService.delete(Long.valueOf(inp));
            }
            redirectAttributes.addFlashAttribute("message","批量删除成功");
        }
        return "redirect:/user/list";
    }

    /**
     * 验证是否唯一
     * @param tbUser
     */
    @ResponseBody
    @RequestMapping(value = "check")
    public boolean checkOnly(TbUser tbUser){
        return tbUserService.checkOnly(tbUser);
    }
}
