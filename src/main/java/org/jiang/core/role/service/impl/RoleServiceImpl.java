package org.jiang.core.role.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jiang.core.role.bean.Role;
import org.jiang.core.role.dao.mapper.RoleMapper;
import org.jiang.core.role.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Li.Linhua
 * @since 2019-07-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
