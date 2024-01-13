package com.flyhigh.form.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 表单对象 form
 *
 * @author flyhigh
 * @date 2022-05-29
 */
@Data
@TableName("form")
public class Form {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关联ID
     */
    @TableField(value = "contact_id")
    private Long contactId;

    /**
     * 系统 保证每个系统数据不冲突
     */
    @TableField(value = "`system`")
    private String system;

    /**
     * 是否可多填表单
     */
    @TableField(value = "is_mult_form")
    private Integer isMultForm;

    /**
     * 表单内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

}
