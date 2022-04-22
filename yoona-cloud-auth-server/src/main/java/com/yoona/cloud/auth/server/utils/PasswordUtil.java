package com.yoona.cloud.auth.server.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-22 14:13
 */
public class PasswordUtil {

    /**
     * 加密
     *
     * @param password
     * @return
     */
    public static String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    /**
     * 校验密码
     *
     * @param password
     * @param dbPassword
     * @return
     */
    public static boolean checkPassword(String password, String dbPassword) {
        return new BCryptPasswordEncoder().matches(password, dbPassword);
    }


}
