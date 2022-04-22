package com.yoona.cloud.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-21 17:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginToken {

    private String accessToken;

    private String refreshToken;
}
