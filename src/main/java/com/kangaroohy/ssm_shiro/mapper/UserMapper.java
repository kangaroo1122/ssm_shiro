package com.kangaroohy.ssm_shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kangaroohy.ssm_shiro.domain.entity.po.User;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kangaroo
 * @since 2019-12-09
 */
@Component
public interface UserMapper extends BaseMapper<User> {
    User selectByUsername(String username);
}
