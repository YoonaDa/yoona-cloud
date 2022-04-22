package com.yoona.cloud.auth.server.controller;

import com.yoona.cloud.common.response.BaseResponse;
import com.yoona.cloud.common.response.SystemResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-22 14:38
 */
@Api(tags = "测试")
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

    @GetMapping("/test1")
    public BaseResponse test1(){
        return SystemResponse.success();
    }

}
