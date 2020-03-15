package com.kangaroohy.ssm_shiro.domain.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.kangaroohy.ssm_shiro.domain.entity.po.Permission;
import com.kangaroohy.ssm_shiro.domain.entity.po.Role;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author kangaroo
 * @since 2019-12-09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleVO implements Serializable {

    /**
     * 角色基本信息
     */
    @JSONField(ordinal = 1)
    private Role role;

    /**
     * 权限集合
     */
    @JSONField(ordinal = 2)
    private List<Permission> permissionList;

}
