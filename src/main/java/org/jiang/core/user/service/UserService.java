package org.jiang.core.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jiang.core.user.bean.User;

import java.util.List;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author Li.Linhua
 * @since 2019-07-15
 */
public interface UserService extends IService<User> {

    /**
     * @description 密码加密保存用户
     * @param user
     * @return {@link boolean}
     * @author Li.Linhua
     * @date 2019/7/17
     */
    boolean encryptPasswordSave(User user);

    /**
     * @description 密码加密后修改用户
     * @param user
     * @return {@link boolean}
     * @author Li.Linhua
     * @date 2019/7/17
     */
    boolean encryptPasswordUpdate(User user);

    /**
     * @description 根据用户名查询用户
     * @param userName
     * @return {@link List< User>}
     * @author Li.Linhua
     * @date 2019/7/17
     */
    List<User> findUserByName(String userName);
}
