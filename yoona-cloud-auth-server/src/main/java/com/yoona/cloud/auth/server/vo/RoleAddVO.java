package com.yoona.cloud.auth.server.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-22 10:46
 */
@Data
public class RoleAddVO {

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    private String roleDescription;

}
