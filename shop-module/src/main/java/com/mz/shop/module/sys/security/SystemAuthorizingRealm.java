package com.mz.shop.module.sys.security;
import com.mz.shop.module.sys.dto.LoginDTO;
import com.mz.shop.module.user.entity.TbUser;
import com.mz.shop.module.user.service.TbUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统安全认证实现类
 * @Title:
 * @Description:
 * @author: chris
 * @version: 1.0.0
 * @date: 2018/01/15 20:29
 */
@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {
    // 角色
    private static final String roleRoot = "root";
    // 权限
    private static final String roleRootPermission = "module:user:form,module:user:list,module:user:save";

    @Autowired
    private TbUserService tbUserService;

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 添加角色
        info.addRole(roleRoot);

        // 添加用户权限
        info.addStringPermission("user");

        // 添加基于 Permission 的权限信息
        for (String s : roleRootPermission.split(",")) {
            info.addStringPermission(s);
        }
        return info;
    }

    /**
     * 认证回调函数, 登录时调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

        // 校验登录验证码
        String validateCode = (String) getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        // 用户输入的验证码为空或与系统验证码不匹配
        if (usernamePasswordToken.getValidateCode() == null || !validateCode.toUpperCase().equals(usernamePasswordToken.getValidateCode().toUpperCase())) {
            throw new AuthenticationException("msg:验证码错误，请重试");
        }
        
        // 校验用户名和密码
        TbUser tbUser = tbUserService.getByLoginId(usernamePasswordToken.getUsername());

        if (tbUser != null) {
            // 获取加密后的密码
            String password = new String(usernamePasswordToken.getPassword());

            // 构建身份信息
            LoginDTO principal = new LoginDTO(usernamePasswordToken.getUsername(), password, usernamePasswordToken.isRememberMe() ? "on" : "", usernamePasswordToken.getValidateCode());

            return new SimpleAuthenticationInfo(principal, tbUser.getPassword(), getName());
        }
        // 登录未通过
        return null;
    }

    /**
     * 获取 Shiro 管理的 Session
     * @return
     */
    private Session getSession() {
        Subject subject = SecurityUtils.getSubject();
        // 是否创建新会话
        Session session = subject.getSession(false);
        if (session == null) {
            session = subject.getSession();
        }
        return session;
    }
}
