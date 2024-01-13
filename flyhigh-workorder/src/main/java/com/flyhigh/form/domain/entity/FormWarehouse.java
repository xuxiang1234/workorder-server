package com.flyhigh.form.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 表表单仓库对象 form_warehouse
 *
 * @author flyhigh
 * @date 2022-08-06
 */
@Data
@TableName("form_warehouse")
public class FormWarehouse {

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
     * 系统
     */
    @TableField(value = "`system`")
    private String system;

    /**
     * 类别
     */
    @TableField(value = "`category`")
    private String category;

    /**
     * 表单名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 是否可以多填表单
     */
    @TableField(value = "is_mult_form")
    private Integer isMultForm;

    /**
     * 表单内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 创建人ID
     */
    @TableField(value = "create_id")
    private Long createId;

    /**
     * 创建人姓名
     */
    @TableField(value = "create_name")
    private String createName;

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

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

}
