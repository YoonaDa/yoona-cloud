package com.yoona.cloud.auth.server.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-22 16:23
 */
@Data
public class UserInfoDTO {

    @ApiModelProperty(value = "唯一的用户id")
    private String userId;

    @ApiModelProperty(value = "微信小程序open_id")
    private String openId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "头像url")
    private String avatar;

    @ApiModelProperty(value = "个性签名")
    private String signature;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "角色列表")
    private List<String> roleList;

}
