package com.flyhigh.sms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 消息对象 biz_message_info
 *
 * @author flyhigh
 * @date 2022-03-13
 */
@Data
@TableName("biz_message_info")
public class BizMessageInfo {

    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 模块
     */
    @TableField(value = "module_type")
    private String moduleType;

    /**
     * 业务主键id
     */
    @TableField(value = "business_id")
    private Long businessId;

    /**
     * 消息类型
     */
    @TableField(value = "message_type")
    private String messageType;

    /**
     * 组件路径
     */
    @TableField(value = "component")
    private String component;

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
