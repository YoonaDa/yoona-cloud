package com.yoona.cloud.auth.server.service.impl;

import com.yoona.cloud.auth.server.entity.Role;
import com.yoona.cloud.auth.server.mapper.RoleMapper;
import com.yoona.cloud.auth.server.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author yoonada
 * @since 2022-04-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
