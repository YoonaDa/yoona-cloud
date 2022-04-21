package com.yoona.cloud.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: yoonada
 * @email: m15602498163@163.com
 * @create: 2021-08-31 17:59
 * @description: 公共响应枚举
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    /**
     * 枚举所有状态
     */
    SUCCESS(200, "成功"),

    FAIL(400, "发生异常"),

    UN_AUTHORIZED(401,"未登录或未授权")

    ;
    private final Integer code;
    private final String description;

}
