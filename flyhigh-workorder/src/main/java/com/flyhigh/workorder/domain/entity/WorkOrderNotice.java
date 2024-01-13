package com.flyhigh.workorder.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 工单消息通知对象 work_order_notice
 *
 * @author flyhigh
 * @date 2022-05-21
 */
@Data
@TableName("work_order_notice")
public class WorkOrderNotice {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 工单ID
     */
    @TableField(value = "work_order_id")
    private Long workOrderId;

    /**
     * 工单名称
     */
    @TableField(value = "work_order_name")
    private String workOrderName;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 是否已读
     */
    @TableField(value = "is_read")
    private Integer isRead;

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
     * 工单类型
     */
    @TableField(exist = false)
    private String workOrderType;

}
