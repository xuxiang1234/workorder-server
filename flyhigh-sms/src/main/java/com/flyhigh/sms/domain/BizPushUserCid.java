package com.flyhigh.sms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 用户个推cid关系绑定对象 biz_push_user_cid
 *
 * @author flyhigh
 * @date 2022-03-31
 */
@Data
@TableName("biz_push_user_cid")
public class BizPushUserCid {

    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * $column.columnComment
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 个推cid
     */
    @TableField(value = "cid")
    private String cid;

    /**
     * 用户别名
     */
    @TableField(value = "user_alias")
    private String userAlias;

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
