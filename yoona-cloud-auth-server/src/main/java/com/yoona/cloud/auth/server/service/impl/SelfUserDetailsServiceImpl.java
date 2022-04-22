package com.yoona.cloud.auth.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yoona.cloud.auth.server.dto.QueryUserDTO;
import com.yoona.cloud.auth.server.entity.SelfUser;
import com.yoona.cloud.auth.server.entity.User;
import com.yoona.cloud.auth.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-22 15:56
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SelfUserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    /**
     * 查询用户信息
     */
    @Override
    public SelfUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByCondition(QueryUserDTO.builder().userName(username).build());
        if (Objects.nonNull(user)){
            return BeanUtil.copyProperties(user, SelfUser.class);
        }
        return null;
    }
}