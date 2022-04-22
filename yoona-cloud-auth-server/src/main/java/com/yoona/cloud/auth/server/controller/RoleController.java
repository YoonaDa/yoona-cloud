package com.yoona.cloud.auth.server.controller;


import com.yoona.cloud.auth.server.service.RoleService;
import com.yoona.cloud.auth.server.vo.RoleAddVO;
import com.yoona.cloud.common.response.BaseResponse;
import com.yoona.cloud.common.response.SystemResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author yoonada
 * @since 2022-04-21
 */
@Api(tags = "角色")
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleController {

    private final RoleService roleService;

    @ApiOperation("新增角色")
    @PostMapping("/add")
    public BaseResponse add(RoleAddVO vo){
        return roleService.add(vo);
    }

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @ApiOperation("列表")
    @GetMapping("/list")
    public BaseResponse list(){
        return SystemResponse.success(roleService.list());
    }

}

