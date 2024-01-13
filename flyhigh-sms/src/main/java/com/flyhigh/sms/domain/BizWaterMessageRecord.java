package com.flyhigh.sms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 业务消息流水对象 biz_water_message_record
 *
 * @author flyhigh
 * @date 2022-04-21
 */
@Data
@TableName("biz_water_message_record")
public class BizWaterMessageRecord {

    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 模块描述
     */
    @TableField(value = "module_type_desc")
    private String moduleTypeDesc;

    /**
     * 模块类型
     */
    @TableField(value = "module_type")
    private Long moduleType;

    /**
     * 业务主键id
     */
    @TableField(value = "business_id")
    private Long businessId;

    /**
     * 消息类型
     */
    @TableField(value = "message_type")
    private Long messageType;

    /**
     * 消息类型描述，防止一个模块多个消息类型
     */
    @TableField(value = "message_desc")
    private String messageDesc;

    /**
     * 自定义参数
     */
    @TableField(value = "custom_parameters")
    private String customParameters;

    /**
     * 消息内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 消息发送者
     */
    @TableField(value = "message_sender")
    private String messageSender;

    /**
     * 创建人id
     */
    @TableField(value = "create_user_id")
    private Long createUserId;

    /**
     * 部门id
     */
    @TableField(value = "dept_id")
    private Long deptId;

    /**
     * 部门名称
     */
    @TableField(value = "dept_name")
    private String deptName;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private String updateBy;

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

    /**
     * 是否删除 0 否 1 是
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

}
