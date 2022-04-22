package com.yoona.cloud.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoona.cloud.common.response.BaseResponse;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-04-21 17:38
 */
public class ResponseUtils {

    public static void result(HttpServletResponse response, BaseResponse baseResponse) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(baseResponse).getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }
}
