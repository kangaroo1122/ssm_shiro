package com.kangaroohy.ssm_shiro.domain.entity.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author kangaroo
 * @since 2019-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(ordinal = 1)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @JSONField(ordinal = 2)
    private String name;

    @JSONField(ordinal = 3)
    private String url;
}
