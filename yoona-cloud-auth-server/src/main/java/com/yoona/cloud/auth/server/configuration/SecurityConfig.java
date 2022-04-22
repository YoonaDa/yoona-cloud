package com.yoona.cloud.auth.server.configuration;

import com.alibaba.fastjson.JSON;
import com.yoona.cloud.auth.server.filter.JwtAuthenticationTokenFilter;
import com.yoona.cloud.auth.server.handler.EntryPointUnauthorizedHandler;
import com.yoona.cloud.auth.server.handler.RequestAccessDeniedHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-21 17:32
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Value("${customize.antMatchers}")
    private String antMatchers;

    @Resource
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    @Resource
    private RequestAccessDeniedHandler requestAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] antMatchersArray = Arrays.stream(antMatchers.split(",")).toArray(String[]::new);
        log.info("放通的拦截为：{}", JSON.toJSONString(antMatchersArray));
        http.formLogin()
                //禁用表单登录，前后端分离用不上
                .disable()
                // 设置URL的授权
                .authorizeRequests()
                // 这里需要将登录页面放行,permitAll()表示不再拦截，/login 登录的url，/refreshToken刷新token的url
                .antMatchers(antMatchersArray)
                .permitAll()
                .anyRequest()
                .authenticated()
                //处理异常情况：认证失败和权限不足
                .and()
                .exceptionHandling()
                //认证未通过，不允许访问异常处理器
                .authenticationEntryPoint(entryPointUnauthorizedHandler)
                //认证通过，但是没权限处理器
                .accessDeniedHandler(requestAccessDeniedHandler)
                .and()
                //禁用session，JWT校验不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationTokenFilter(authenticationManager()))
                // 关闭csrf
                .csrf().disable();
    }

    /**
     * 加密算法
     *
     * @return
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}