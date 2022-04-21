package com.yoona.cloud.common.response;

import com.yoona.cloud.common.enums.ResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yoonada
 * @email: m15602498163@163.com
 * @create: 2021-09-06 20:49
 * @description: 基本响应
 */
@Data
@ApiModel(value = "基本响应")
@NoArgsConstructor
public class BaseResponse {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "响应信息")
    private String msg;

    @ApiModelProperty(value = "响应时间")
    private Long timestamp = System.currentTimeMillis() / 1000;


    public BaseResponse(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getDescription();
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    public BaseResponse(ResponseEnum responseEnum, String msg) {
        this.code = responseEnum.getCode();
        this.msg = msg;
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    public static BaseResponse isFailed(String exceptions) {
        return new BaseResponse(ResponseEnum.FAIL, exceptions);
    }

    public static BaseResponse isFailed(ResponseEnum responseEnum,String exceptions) {
        return new BaseResponse(responseEnum, exceptions);
    }

    public static BaseResponse isSuccessful() {
        return new BaseResponse(ResponseEnum.SUCCESS);
    }



}