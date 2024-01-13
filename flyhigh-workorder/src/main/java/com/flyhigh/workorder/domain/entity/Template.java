package com.flyhigh.workorder.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 模板对象 template
 *
 * @author flyhigh
 * @date 2022-05-14
 */
@Data
@TableName("template")
public class Template {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 模板名称
     */
    @TableField(value = "template_name")
    private String templateName;

    /**
     * 模板类型
     */
    @TableField(value = "template_type")
    private String templateType;

    /**
     * 模板描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 模板图片地址 多个逗号分隔
     */
    @TableField(value = "pic_url")
    private String picUrl;

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


}
