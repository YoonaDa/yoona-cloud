package com.yoona.cloud.auth.server.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description: 登录或注册
 * @date 2022-04-22 10:02
 */
@Data
public class RegisterVO {

    @ApiModelProperty(value = "微信小程序open_id")
    private String openId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

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

}
