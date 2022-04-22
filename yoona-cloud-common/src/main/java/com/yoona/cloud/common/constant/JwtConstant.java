package com.yoona.cloud.common.constant;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-21 17:45
 */
public class JwtConstant {

    /**
     * 秘钥
     */
    public static final String SECRET = "yoona-cloud";

    /**
     * 颁发者
     */
    public static final String ISSUER = "yoona-cloud";

    /**
     * 过期时间 毫秒
     */
    public static final long EXPIRATION = 7*24*60*60*1000;


    /**
     * Token前缀
     */

    public static final String TOKEN_PREFIX = "Bearer";

    /**
     * 存放Token的Header
     */

    public static final String HEADER_STRING = "Authorization";

}
