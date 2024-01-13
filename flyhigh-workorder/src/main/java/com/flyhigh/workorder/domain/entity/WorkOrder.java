package com.flyhigh.workorder.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 工单对象 work_order
 *
 * @author flyhigh
 * @date 2022-05-17
 */
@Data
@TableName("work_order")
public class WorkOrder {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 工单名称
     */
    @TableField(value = "work_order_name")
    private String workOrderName;

    /**
     * 工单类型
     */
    @TableField(value = "work_order_type")
    private String workOrderType;

    /**
     * 工单等级
     */
    @TableField(value = "work_order_level")
    private String workOrderLevel;

    /**
     * 预计开始时间
     */
    @TableField(value = "start_time")
    private Date startTime;

    /**
     * 设备名称
     */
    @TableField(value = "device_name")
    private String deviceName;

    /**
     * 位置
     */
    @TableField(value = "position")
    private String position;

    /**
     * 来源
     */
    @TableField(value = "source")
    private String source;

    /**
     * 工单描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 工单图片地址 多个逗号分隔
     */
    @TableField(value = "pic_url")
    private String picUrl;

    /**
     * 工单状态 未完成 已完成
     */
    @TableField(value = "status")
    private String status;

    /**
     * 当前处理人ID
     */
    @TableField(value = "handle_user_id")
    private Long handleUserId;

    /**
     * 是否驳回
     */
    @TableField(value = "is_reject")
    private Integer isReject;

    /**
     * 评价
     */
    @TableField(value = "evaluate")
    private String evaluate;

    /**
     * 评分
     */
    @TableField(value = "score")
    private Integer score;

    /**
     * 报价
     */
    @TableField(value = "offer")
    private String offer;

    /**
     * 科室
     */
    @TableField(value = "office")
    private String office;

    /**
     * 报修人
     */
    @TableField(value = "repair_name")
    private String repairName;

    /**
     * 报修人ID
     */
    @TableField(value = "repair_id")
    private String repairId;

    /**
     * 完成时间
     */
    @TableField(value = "complete_time")
    private Date completeTime;

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
