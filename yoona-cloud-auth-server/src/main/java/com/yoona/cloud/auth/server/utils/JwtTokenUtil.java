package com.yoona.cloud.auth.server.utils;

import com.alibaba.fastjson.JSON;
import com.yoona.cloud.auth.server.entity.SelfUser;
import com.yoona.cloud.common.constant.JwtConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @Email: m15602498163@163.com
 * @Author: yoonada
 * @Date: 2020/7/27
 * @Time: 3:29 下午
 * @Msg: Jwt工具类
 */
@Slf4j
public class JwtTokenUtil {



    /**
     * 生成token
     * @param selfUser
     * @return
     */
    public static String createAccessToken(SelfUser selfUser) {
        // 登陆成功生成JWT
        return Jwts.builder()
                // 放入用户名和用户ID
                .setId(selfUser.getUserId())
                // 主题
                .setSubject(selfUser.getUsername())
                // 签发时间
                .setIssuedAt(new Date())
                // 签发者
                .setIssuer(JwtConstant.ISSUER)
                // 自定义属性 放入用户拥有权限
                .claim("authorities", JSON.toJSONString(selfUser.getAuthorities()))
                // 失效时间
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstant.EXPIRATION))
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, JwtConstant.SECRET)
                .compact();
    }
}