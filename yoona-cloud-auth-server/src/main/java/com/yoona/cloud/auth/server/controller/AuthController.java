package com.yoona.cloud.auth.server.controller;

import com.yoona.cloud.auth.server.service.AuthService;
import com.yoona.cloud.auth.server.vo.LoginVO;
import com.yoona.cloud.auth.server.vo.RegisterVO;
import com.yoona.cloud.common.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description: 认证
 * @date 2022-04-22 09:57
 */
@Api(tags = "认证")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final AuthService authService;

    @ApiOperation("注册")
    @PostMapping("/register")
    public BaseResponse register(RegisterVO vo){
        return authService.register(vo);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public BaseResponse login(LoginVO vo){
        return authService.login(vo);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/getUserInfo")
    public BaseResponse getUserInfo(){
        return authService.getUserInfo();
    }

}
