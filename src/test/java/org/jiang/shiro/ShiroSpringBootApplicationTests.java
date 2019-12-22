package org.jiang.shiro;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.jiang.core.user.bean.User;
import org.jiang.core.user.service.UserService;
import org.jiang.shiro.encrypt.MyEncypt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroSpringBootApplicationTests {

    Logger logger = LoggerFactory.getLogger(ShiroSpringBootApplicationTests.class);

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultWebSecurityManager defaultWebSecurityManager;
    @Test
    public void saveUser() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUserName("lilinhua");
        user.setPassword("111111");
        boolean result = userService.encryptPasswordSave(user);
        logger.info("result:"+result+";"+user.toString());//salt:root7fca63477993c5741c70643215e5f88a
    }

    @Test
    public void findByUserName(){
        List<User> users = userService.findUserByName("lilinhua");
        users.forEach(System.out::println);
        logger.info("users:"+users.size());
    }
    @Test
    public void page(){
        Page<User> page = new Page<>();
        page.setSize(2);
        IPage<User> page1 = userService.page(page);
        logger.info("current:"+page1.getCurrent());
        List<User> records = page1.getRecords();
        records.forEach(System.out::println);
        IPage<User> page2 = userService.page(page1);
        List<User> records1 = page2.getRecords();
        records1.forEach(System.out::println);
    }

    @Test
    public void login(){
        SecurityUtils.setSecurityManager(defaultWebSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("root","222222");
        try{
            subject.login(usernamePasswordToken);
        }catch (UnknownAccountException e){
            logger.info("登录失败："+e.getMessage());
        }
    }

    @Test
    public void encypt(){
        String pass = MyEncypt.encrypt("111111", "lilinhuab2088a4eceaf5915b9d1642b419be6b3");
        logger.info("encypt password:"+pass);
    }
}
