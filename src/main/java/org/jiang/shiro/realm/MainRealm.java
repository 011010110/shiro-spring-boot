package org.jiang.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.jiang.core.user.bean.User;
import org.jiang.core.user.service.UserService;
import org.jiang.shiro.encrypt.MyEncypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Li.Linhua
 * @description 自定义主要realm
 * @Date 2019/7/9
 */
@Component
public class MainRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(MainRealm.class);

    @Autowired
    private UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("用户登录");
        if(!(authenticationToken instanceof UsernamePasswordToken)){
            throw new UnsupportedTokenException("验证token不支持");
        }
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        char[] password = usernamePasswordToken.getPassword();
        String principal = username;
        String credentials = String.valueOf(password);
        List<User> users = userService.findUserByName(principal);
        users.forEach(System.out::println);
        if(users==null||users.isEmpty()){
            logger.info("登录名错误,principal："+principal);
            throw new UnknownAccountException("登录名错误");
        }
        boolean match = false;
        for(User user:users){
            String password1 = user.getPassword();
            String salt = user.getSalt();
            String encrypt = MyEncypt.encrypt(credentials, salt);
            logger.info("encypt:"+encrypt+";password1:"+password1);
            if(encrypt.equals(password1)){
                match = true;
                break;
            }
        }
        if(!match){
            logger.info("密码匹配失败");
            throw new UnknownAccountException("密码匹配失败");
        }
        logger.info("匹配成功");
        //principal，credentials是从数据库中获取的用户信息，从而返回SimpleAuthenticationInfo,已供认证使用
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal,credentials,getName());

        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addStringPermission("menu:*");
        return simpleAuthorizationInfo;
    }
}
