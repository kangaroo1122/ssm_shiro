package com.kangaroohy.ssm_shiro.service.impl;

import com.kangaroohy.ssm_shiro.domain.entity.po.Role;
import com.kangaroohy.ssm_shiro.mapper.RoleMapper;
import com.kangaroohy.ssm_shiro.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
