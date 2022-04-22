package com.yoona.cloud.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-22 10:25
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

    /**
     * 枚举所有状态
     */

    ENABLE("enable","启用"),

    DISABLED("disabled","禁用")

    ;

    private final String code;

    private final String description;

}
