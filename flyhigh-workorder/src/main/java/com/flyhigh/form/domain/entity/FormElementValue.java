package com.flyhigh.form.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 表单元素值对象 form_element_value
 *
 * @author flyhigh
 * @date 2022-05-29
 */
@Data
@TableName("form_element_value")
public class FormElementValue {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 元素ID
     */
    @TableField(value = "form_element_id")
    private Long formElementId;

    /**
     * 值索引
     */
    @TableField(value = "value_index")
    private Integer valueIndex;

    /**
     * 元素值
     */
    @TableField(value = "value")
    private String value;

    /**
     * 是否数字
     */
    @TableField(value = "is_number")
    private Integer isNumber;

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
