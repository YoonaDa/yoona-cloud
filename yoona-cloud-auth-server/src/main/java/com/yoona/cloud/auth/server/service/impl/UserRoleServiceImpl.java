package com.yoona.cloud.auth.server.service.impl;

import com.yoona.cloud.auth.server.entity.UserRole;
import com.yoona.cloud.auth.server.mapper.UserRoleMapper;
import com.yoona.cloud.auth.server.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户角色关联表 服务实现类
 * </p>
 *
 * @author yoonada
 * @since 2022-04-21
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
