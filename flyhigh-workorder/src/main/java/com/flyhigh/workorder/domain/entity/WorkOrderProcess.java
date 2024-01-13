package com.flyhigh.workorder.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 工单流程对象 work_order_process
 *
 * @author flyhigh
 * @date 2022-05-17
 */
@Data
@TableName("work_order_process")
public class WorkOrderProcess {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 工单ID
     */
    @TableField(value = "work_order_id")
    private Long workOrderId;

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
     * 是否新增
     */
    @TableField(value = "is_new")
    private Integer isNew;

    /**
     * 是否最后一个流程
     */
    @TableField(value = "is_last")
    private Integer isLast;

    /**
     * 是否是调度员处理
     */
    @TableField(value = "is_dispatcher_user")
    private Boolean isDispatcherUser;

    /**
     * 工单流程状态 驳回 确认
     */
    @TableField(value = "status")
    private String status;

    /**
     * 评价
     */
    @TableField(value = "evaluate")
    private String evaluate;

    /**
     * 评价图片
     */
    @TableField(value = "evaluate_pic_url")
    private String evaluatePicUrl;

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
