package com.yoona.cloud.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-22 10:30
 */
@Getter
@AllArgsConstructor
public enum RoleEnum {

    /**
     * 枚举所有角色
     */
    ROLE_ADMIN("管理员"),

    ROLE_SUPER_ADMIN("超级管理员"),

    ROLE_USER("用户"),

    ROLE_VISITOR("游客");

    private final String description;

}
