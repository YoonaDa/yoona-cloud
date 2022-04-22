package com.yoona.cloud.auth.server.service;

import com.yoona.cloud.auth.server.dto.QueryUserDTO;
import com.yoona.cloud.auth.server.entity.Role;
import com.yoona.cloud.auth.server.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author yoonada
 * @since 2022-04-21
 */
public interface UserService extends IService<User> {

    /**
     * 通过userId获取用户
     * @param userId
     * @return
     */
    User getUserByUserId(String userId);


    /**
     * 根据条件获取user
     * @param dto
     * @return
     */
    User getUserByCondition(QueryUserDTO dto);

    /**
     * 通过userId获取角色列表
     * @param userId
     * @return
     */
    List<Role> selectRoleByUserId(String userId);
}
