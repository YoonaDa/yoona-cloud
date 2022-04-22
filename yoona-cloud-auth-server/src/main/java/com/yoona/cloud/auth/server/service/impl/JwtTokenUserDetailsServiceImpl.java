package com.yoona.cloud.auth.server.service.impl;

import com.yoona.cloud.auth.server.entity.SecurityUser;
import com.yoona.cloud.auth.server.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-22 09:40
 */
@Service
public class JwtTokenUserDetailsServiceImpl implements UserDetailsService {

    /**
     * 查询用户详情的service
     */
    @Autowired
    private LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库中查询
        SecurityUser securityUser = loginService.loadByUsername(username);
        //用户不存在直接抛出UsernameNotFoundException，security会捕获抛出BadCredentialsException
        if (Objects.isNull(securityUser)) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        return securityUser;
    }
}
