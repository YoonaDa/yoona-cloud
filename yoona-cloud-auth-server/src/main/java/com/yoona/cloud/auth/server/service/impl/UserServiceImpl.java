package com.yoona.cloud.auth.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yoona.cloud.auth.server.dto.QueryUserDTO;
import com.yoona.cloud.auth.server.entity.Role;
import com.yoona.cloud.auth.server.entity.User;
import com.yoona.cloud.auth.server.mapper.UserMapper;
import com.yoona.cloud.auth.server.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author yoonada
 * @since 2022-04-21
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 通过userId获取用户
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserByUserId(String userId) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getIsDelete, 0)
                .eq(User::getUserId, userId);
        return getOne(lambdaQueryWrapper);
    }

    /**
     * 根据条件获取user
     * @param dto
     * @return
     */
    @Override
    public User getUserByCondition(QueryUserDTO dto) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getIsDelete, 0)
                .eq(StringUtils.isNotBlank(dto.getUserId()), User::getUserId, dto.getUserId())
                .eq(StringUtils.isNotBlank(dto.getUserName()), User::getUsername, dto.getUserName());
        return getOne(lambdaQueryWrapper);
    }

    @Override
    public List<Role> selectRoleByUserId(String userId) {
        return userMapper.selectRoleByUserId(userId);
    }
}
