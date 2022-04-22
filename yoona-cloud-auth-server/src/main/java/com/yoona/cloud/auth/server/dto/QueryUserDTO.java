package com.yoona.cloud.auth.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-22 15:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryUserDTO {

    private String userId;

    private String userName;

}
