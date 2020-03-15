package com.kangaroohy.ssm_shiro.domain.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.kangaroohy.ssm_shiro.domain.entity.po.User;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author kangaroo
 * @since 2019-12-09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    /**
     * 用户信息
     */
    @JSONField(ordinal = 1)
    private User user;

    /**
     * 角色集合
     */
    @JSONField(ordinal = 2)
    private List<RoleVO> roleList;
}
