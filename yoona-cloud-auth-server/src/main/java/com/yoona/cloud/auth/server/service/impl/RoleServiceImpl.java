package com.yoona.cloud.auth.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yoona.cloud.auth.server.entity.Role;
import com.yoona.cloud.auth.server.mapper.RoleMapper;
import com.yoona.cloud.auth.server.service.RoleService;
import com.yoona.cloud.auth.server.vo.RoleAddVO;
import com.yoona.cloud.common.constant.Constant;
import com.yoona.cloud.common.response.BaseResponse;
import com.yoona.cloud.common.response.SystemResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author yoonada
 * @since 2022-04-21
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public BaseResponse add(RoleAddVO vo) {
        String roleName = vo.getRoleName();
        if (!roleName.contains(Constant.ROLE_PREFIX)) {
            roleName = String.join("", Constant.ROLE_PREFIX, roleName);
        }
        String roleNameResult = roleName.toUpperCase();
        LambdaQueryWrapper<Role> lambdaQueryWrapper = new LambdaQueryWrapper<Role>()
                .eq(Role::getIsDelete, 0)
                .eq(Role::getRoleName, roleNameResult);
        if (count(lambdaQueryWrapper) > 0) {
            return SystemResponse.fail("当前角色已存在");
        }
        Role role = BeanUtil.copyProperties(vo, Role.class);
        role.setRoleName(roleNameResult);
        role.setRoleId(IdUtil.simpleUUID());
        boolean save = save(role);
        if (save) {
            return SystemResponse.success("新增角色成功");
        }
        return SystemResponse.fail("新增角色失败");
    }
}
