package com.kangaroohy.ssm_shiro.domain.entity.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author kangaroo
 * @since 2019-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(ordinal = 1)
    private Integer id;

    /**
     * 用户名
     */
    @JSONField(ordinal = 2)
    private String username;

    /**
     * 密码
     */
    @JSONField(ordinal = 3)
    private String password;

    /**
     * 性别（0：男，1：女）
     */
    @JSONField(ordinal = 4)
    private Integer gender;

    /**
     * 添加时间
     */
    @JSONField(ordinal = 5)
    private Date updateTime;
}
