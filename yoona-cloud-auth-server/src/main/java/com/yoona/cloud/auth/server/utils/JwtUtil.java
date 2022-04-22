package com.yoona.cloud.auth.server.utils;

import com.alibaba.fastjson.JSON;
import com.yoona.cloud.auth.server.dto.TokenParameterDTO;
import com.yoona.cloud.auth.server.entity.SecurityUser;
import com.yoona.cloud.common.constant.JwtConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-21 17:44
 */
@Component
@Data
public class JwtUtil {


    public String createToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                //生成时间
                .setIssuedAt(new Date())
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstant.EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, JwtConstant.SECRET)
                .compact();
    }

    /**
     * 生成accessToken
     *
     * @param dto
     * @return
     */
    public static String generateAccessToken(TokenParameterDTO dto) {
        return Jwts.builder()
                .setSubject(JSON.toJSONString(dto))
                //生成时间
                .setIssuedAt(new Date())
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstant.EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, JwtConstant.SECRET)
                .compact();
    }

    /**
     * 生成refreshToken
     *
     * @param accessToken
     * @return
     */
    public static String generateRefreshToken(String accessToken) {
        return refreshToken(accessToken);
    }


    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private static String generateToken(Map<String, Object> claims, Long expiration) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, JwtConstant.SECRET).compact();
    }


    /**
     * 从token中解析出数据
     *
     * @param token 令牌
     * @return
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(JwtConstant.SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            claims = null;
        }
        return claims;
    }


    /**
     * 根据token获取username
     *
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        return JSON.parseObject(getClaimsFromToken(token).getSubject(), TokenParameterDTO.class).getUsername();
    }

    public String getUserIdFromToken(String token) {
        return JSON.parseObject(getClaimsFromToken(token).getSubject(), TokenParameterDTO.class).getUserId();
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(Claims.ISSUED_AT, new Date());
            refreshedToken = generateToken(claims, 2 * JwtConstant.EXPIRATION);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证令牌
     *
     * @param token       令牌
     * @param userDetails 用户
     * @return 是否有效
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        SecurityUser user = (SecurityUser) userDetails;
        String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }


    /**
     * 验证令牌
     *
     * @param token
     * @param userId
     * @return
     */
    public Boolean validateToken(String token, String userId) {
        String tokenUserId = getUserIdFromToken(token);
        return (tokenUserId.equals(userId) && !isTokenExpired(token));
    }


}
