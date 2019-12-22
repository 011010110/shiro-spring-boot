/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jiang.shiro.samples;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.jiang.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);
    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/login.html")
    public String loginTemplate() {
        return "login";
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("login.json")
    @ResponseBody
    public Result login(String username,String password){
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(usernamePasswordToken);
        }catch (AccountException e){
            return Result.error().setMsg("账户错误");
        }catch (CredentialsException e){
            return Result.error().setMsg("密码错误");
        }catch (UnsupportedTokenException e){
            return Result.error().setMsg("不支持的验证Token");
        }catch (AuthenticationException e){
            return Result.error().setMsg("登录失败");
        }
        return Result.success().setMsg("登录成功");
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = {"accountlogout.html","accountlogout"})
    @ResponseBody
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        logger.info("退出登录：user="+subject.getPrincipals().getPrimaryPrincipal());
        subject.logout();
        return Result.success().setMsg("退出登录");
    }
}
