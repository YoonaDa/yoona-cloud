package com.yoona.cloud.common.response;

import com.yoona.cloud.common.enums.ResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author: yoonada
 * @email: m15602498163@163.com
 * @create: 2021-08-31 18:02
 * @description: 系统响应
 */
@Data
@ApiModel(value = "系统响应")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SystemResponse<T> extends BaseResponse{

    @ApiModelProperty(value = "响应结果")
    private T result;

    @ApiModelProperty(value = "响应个数")
    private Integer amount;

    public SystemResponse(ResponseEnum responseEnum, T result, Integer amount) {
        this.setCode(responseEnum.getCode());
        this.setMsg(responseEnum.getDescription());
        this.result = result;
        this.amount = amount;
    }

    /**
     * 响应成功
     * @param <T>
     * @return
     */
    public static <T> SystemResponse<T> success() {
        return new SystemResponse<>(ResponseEnum.SUCCESS, null, null);
    }

    /**
     * 响应成功
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> SystemResponse<T> success(T result) {
        return new SystemResponse<>(ResponseEnum.SUCCESS, result, 1);
    }

    /**
     * 响应成功
     *
     * @param result
     * @param amount
     * @param <T>
     * @return
     */
    public static <T> SystemResponse<T> success(T result, Integer amount) {
        return new SystemResponse<>(ResponseEnum.SUCCESS, result, amount);
    }

    /**
     * 响应失败
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> SystemResponse<T> fail(T result) {
        return new SystemResponse<>(ResponseEnum.FAIL, result, 1);
    }
}