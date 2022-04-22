package com.yoona.cloud.auth.server.service;

import com.yoona.cloud.auth.server.vo.RegisterVO;
import com.yoona.cloud.common.response.BaseResponse;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-22 10:04
 */
public interface AuthService {

    /**
     * 注册
     * @param vo
     * @return
     */
    BaseResponse register(RegisterVO vo);
}
