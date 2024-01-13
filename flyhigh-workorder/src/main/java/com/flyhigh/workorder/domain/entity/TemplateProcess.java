package com.flyhigh.workorder.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 模板流程对象 template_process
 *
 * @author flyhigh
 * @date 2022-05-15
 */
@Data
@TableName("template_process")
public class TemplateProcess {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 模板编号
     */
    @TableField(value = "template_id")
    private Long templateId;

    /**
     * 流程类型: 人员 组 系统
     */
    @TableField(value = "process_type")
    private String processType;

    /**
     * 流程位置 从1开始
     */
    @TableField(value = "process_index")
    private Integer processIndex;

    /**
     * 组ID
     */
    @TableField(value = "group_id")
    private Long groupId;

    /**
     * 指定人ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 指定人名称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 指定别名
     */
    @TableField(value = "alias_name")
    private String aliasName;

    /**
     * 是否最后一个流程
     */
    @TableField(value = "is_last")
    private Integer isLast;

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
