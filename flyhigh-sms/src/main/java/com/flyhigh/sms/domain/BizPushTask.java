package com.flyhigh.sms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 推送任务对象 biz_push_task
 *
 * @author flyhigh
 * @date 2022-03-31
 */
@Data
@TableName("biz_push_task")
public class BizPushTask {

    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * $column.columnComment
     */
    @TableField(value = "message_id")
    private Long messageId;

    /**
     * 个推task_id
     */
    @TableField(value = "task_id")
    private String taskId;

    /**
     * 个推code
     */
    @TableField(value = "gt_code")
    private String gtCode;

    /**
     * 消息发送人数量
     */
    @TableField(value = "user_number")
    private Long userNumber;

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

    /**
     * 是否删除 0 否 1 是
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

}
