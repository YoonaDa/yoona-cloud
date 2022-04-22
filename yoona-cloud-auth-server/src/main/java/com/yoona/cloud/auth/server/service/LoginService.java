package com.yoona.cloud.auth.server.service;

import com.yoona.cloud.auth.server.entity.SecurityUser;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-22 09:41
 */
public interface LoginService {

    /**
     * 根据用户名查找
     */
    SecurityUser loadByUsername(String username);

}
