package com.yoona.cloud.auth.server.mapper;

import com.yoona.cloud.auth.server.entity.Role;
import com.yoona.cloud.auth.server.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author yoonada
 * @since 2022-04-21
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过userId获取角色列表
     * @param userId
     * @return
     */
    List<Role> selectRoleByUserId(String userId);
}
