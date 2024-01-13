package com.flyhigh.form.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 表单元素对象 form_element
 *
 * @author flyhigh
 * @date 2022-05-29
 */
@Data
@TableName("form_element")
public class FormElement {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 表单ID
     */
    @TableField(value = "form_id")
    private Long formId;

    /**
     * 元素CODE 同一表单CODE唯一
     */
    @TableField(value = "code")
    private String code;

    /**
     * 元素标题
     */
    @TableField(value = "label")
    private String label;

    /**
     * 元素类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 业务名称
     */
    @TableField(value = "biz_name")
    private String bizName;

    /**
     * 业务字段名
     */
    @TableField(value = "biz_field")
    private String bizField;

    /**
     * 业务字段分组 key
     */
    @TableField(value = "biz_key")
    private String bizKey;

    /**
     * 表单索引
     */
    @TableField(value = "form_index")
    private Integer formIndex;

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
