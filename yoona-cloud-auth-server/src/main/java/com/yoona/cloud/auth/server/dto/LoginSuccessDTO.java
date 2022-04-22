package com.yoona.cloud.auth.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-22 14:29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginSuccessDTO {

    private String accessToken;

    private String refreshToken;

}
