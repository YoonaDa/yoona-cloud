package com.yoona.cloud.auth.server.service;

import com.yoona.cloud.auth.server.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yoona.cloud.auth.server.vo.RoleAddVO;
import com.yoona.cloud.common.response.BaseResponse;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author yoonada
 * @since 2022-04-21
 */
public interface RoleService extends IService<Role> {

    /**
     * 新增角色
     * @param vo
     * @return
     */
    BaseResponse add(RoleAddVO vo);
}
