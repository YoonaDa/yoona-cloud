package com.yoona.cloud.auth.server.handler;

import com.yoona.cloud.common.response.BaseResponse;
import com.yoona.cloud.common.response.SystemResponse;
import com.yoona.cloud.common.utils.ResponseUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-21 17:36
 */
@Component
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    /**
     * 一旦登录失败则会被调用
     *
     * @param httpServletRequest
     * @param response
     * @param exception          这个参数是异常信息，可以根据不同的异常类返回不同的提示信息
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        //TODO 根据项目需要返回指定异常提示，陈某这里演示了一个用户名密码错误的异常
        if (exception instanceof BadCredentialsException) {
            ResponseUtils.result(response, BaseResponse.isFailed("用户名或密码不正确"));
        }
        ResponseUtils.result(response, BaseResponse.isFailed("登录失败"));
    }
}