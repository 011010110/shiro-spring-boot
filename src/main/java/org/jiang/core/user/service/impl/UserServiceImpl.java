package org.jiang.core.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.jiang.core.user.bean.User;
import org.jiang.core.user.dao.mapper.UserMapper;
import org.jiang.core.user.service.UserService;
import org.jiang.shiro.encrypt.MyEncypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author Li.Linhua
 * @since 2019-07-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public boolean encryptPasswordSave(User user){
        user.setId(UUID.randomUUID().toString());
        String oldPassword = encryptPassword(user);//密码加密
        logger.debug("用户密码加密,user"+user.toString()+";oldPassword"+oldPassword);
        int insert = baseMapper.insert(user);
        return insert>0;
    }

    @Override
    public boolean encryptPasswordUpdate(User user){
        String oldPassword = encryptPassword(user);//密码加密
        logger.debug("用户密码加密,user"+user.toString()+";oldPassword"+oldPassword);
        int update = baseMapper.updateById(user);
        return update>0;
    }

    @Override
    public List<User> findUserByName(String userName){
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper.eq(User::getUserName,userName);
        List<User> users = baseMapper.selectList(lambdaQueryWrapper);
        return users;
    }

    private String encryptPassword(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        String random = new SecureRandomNumberGenerator().nextBytes().toHex();
        String salt = userName + random;
        String encrypt = MyEncypt.encrypt(password, salt);
        user.setSalt(salt);
        user.setPassword(encrypt);
        return password;
    }
}
