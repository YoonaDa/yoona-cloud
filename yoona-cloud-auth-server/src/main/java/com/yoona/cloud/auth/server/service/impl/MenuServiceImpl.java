package com.yoona.cloud.auth.server.service.impl;

import com.yoona.cloud.auth.server.entity.Menu;
import com.yoona.cloud.auth.server.mapper.MenuMapper;
import com.yoona.cloud.auth.server.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author yoonada
 * @since 2022-04-21
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
