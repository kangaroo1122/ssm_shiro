package com.kangaroohy.ssm_shiro.mapper;

import com.kangaroohy.ssm_shiro.domain.entity.po.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kangaroo
 * @since 2019-12-09
 */
@Component
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> selectRolesByUserId(Integer uid);
}
