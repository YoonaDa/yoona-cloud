package com.yoona.cloud.auth.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yoona.cloud.auth.server.entity.Role;
import com.yoona.cloud.auth.server.entity.User;
import com.yoona.cloud.auth.server.entity.UserRole;
import com.yoona.cloud.auth.server.service.AuthService;
import com.yoona.cloud.auth.server.service.RoleService;
import com.yoona.cloud.auth.server.service.UserRoleService;
import com.yoona.cloud.auth.server.service.UserService;
import com.yoona.cloud.auth.server.vo.RegisterVO;
import com.yoona.cloud.common.enums.RoleEnum;
import com.yoona.cloud.common.enums.StatusEnum;
import com.yoona.cloud.common.response.BaseResponse;
import com.yoona.cloud.common.response.SystemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-22 10:04
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final RoleService roleService;

    private final UserRoleService userRoleService;

    @Override
    public BaseResponse register(RegisterVO vo) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getIsDelete, 0)
                .eq(User::getUsername, vo.getUsername());
        if (userService.count(lambdaQueryWrapper) > 0) {
            return SystemResponse.fail("账号已存在，请前往登录");
        }
        User user = BeanUtil.copyProperties(vo, User.class);
        String userId = IdUtil.simpleUUID();
        user.setUserId(userId);
        user.setPassword(new BCryptPasswordEncoder().encode(vo.getPassword()));
        user.setStatus(StatusEnum.ENABLE.getCode());
        boolean userSave = userService.save(user);
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<Role>()
                .eq(Role::getIsDelete, 0)
                .eq(Role::getRoleName, RoleEnum.ROLE_USER.name());
        Role role = roleService.getOne(wrapper);
        UserRole userRole = UserRole.builder().userId(userId).roleId(role.getRoleId()).build();
        boolean userRoleSave = userRoleService.save(userRole);
        if (userSave && userRoleSave) {
            return SystemResponse.success("注册成功");
        }
        return SystemResponse.fail("注册失败");
    }

}
