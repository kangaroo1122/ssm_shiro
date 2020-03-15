package com.kangaroohy.ssm_shiro.service.impl;

import com.kangaroohy.ssm_shiro.domain.entity.po.Permission;
import com.kangaroohy.ssm_shiro.mapper.PermissionMapper;
import com.kangaroohy.ssm_shiro.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kangaroo
 * @since 2019-12-09
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
