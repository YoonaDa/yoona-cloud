package com.yoona.cloud.auth.server.service.impl;

import com.yoona.cloud.auth.server.entity.User;
import com.yoona.cloud.auth.server.mapper.UserMapper;
import com.yoona.cloud.auth.server.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author yoonada
 * @since 2022-04-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
