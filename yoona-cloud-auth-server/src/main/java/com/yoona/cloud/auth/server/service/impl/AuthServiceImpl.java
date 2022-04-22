package com.yoona.cloud.auth.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yoona.cloud.auth.server.dto.UserInfoDTO;
import com.yoona.cloud.auth.server.entity.Role;
import com.yoona.cloud.auth.server.entity.SelfUser;
import com.yoona.cloud.auth.server.entity.User;
import com.yoona.cloud.auth.server.entity.UserRole;
import com.yoona.cloud.auth.server.service.AuthService;
import com.yoona.cloud.auth.server.service.RoleService;
import com.yoona.cloud.auth.server.service.UserRoleService;
import com.yoona.cloud.auth.server.service.UserService;
import com.yoona.cloud.auth.server.utils.JwtTokenUtil;
import com.yoona.cloud.auth.server.utils.PasswordUtil;
import com.yoona.cloud.auth.server.vo.LoginVO;
import com.yoona.cloud.auth.server.vo.RegisterVO;
import com.yoona.cloud.common.enums.RoleEnum;
import com.yoona.cloud.common.enums.StatusEnum;
import com.yoona.cloud.common.response.BaseResponse;
import com.yoona.cloud.common.response.SystemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

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

    private final SelfUserDetailsServiceImpl selfUserDetailsService;

    /**
     * 注册
     * @param vo
     * @return
     */
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
        user.setPassword(PasswordUtil.encodePassword(vo.getPassword()));
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

    /**
     * 登录
     * @param vo
     * @return
     */
    @Override
    public BaseResponse login(LoginVO vo) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getIsDelete, 0)
                .eq(User::getUsername, vo.getUsername());
        User user = userService.getOne(lambdaQueryWrapper);
        if (Objects.isNull(user)) {
            return SystemResponse.fail("用户名不存在");
        }
        if (!PasswordUtil.checkPassword(vo.getPassword(), user.getPassword())) {
            return SystemResponse.fail("密码错误");
        }
        if (StatusEnum.DISABLED.getCode().equals(user.getStatus())){
            return SystemResponse.fail("账号已被冻结，请联系管理员");
        }
        SelfUser selfUser = selfUserDetailsService.loadUserByUsername(vo.getUsername());
        Set<GrantedAuthority> authorities = getGrantedAuthorities(selfUser);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(selfUser, selfUser.getPassword(), authorities);
        String token = JwtTokenUtil.createAccessToken((SelfUser) authenticationToken.getPrincipal());
        return SystemResponse.success(token);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @Override
    public BaseResponse getUserInfo() {
        SelfUser selfUser = (SelfUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByUserId(selfUser.getUserId());
        UserInfoDTO userInfoDTO = BeanUtil.copyProperties(user, UserInfoDTO.class);
        List<String> roleList = new ArrayList<>();
        selfUser.getAuthorities().forEach(res -> roleList.add(res.getAuthority()));
        userInfoDTO.setRoleList(roleList);
        return SystemResponse.success(userInfoDTO);
    }


    /**
     * 获取角色列表
     * @param selfUser
     * @return
     */
    private Set<GrantedAuthority> getGrantedAuthorities(SelfUser selfUser) {
        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户角色
        List<Role> roleList = userService.selectRoleByUserId(selfUser.getUserId());
        roleList.forEach(res -> authorities.add(new SimpleGrantedAuthority(res.getRoleName())));
        selfUser.setAuthorities(authorities);
        return authorities;
    }

}
