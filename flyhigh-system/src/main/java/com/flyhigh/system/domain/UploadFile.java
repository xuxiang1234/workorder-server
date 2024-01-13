package com.flyhigh.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 上传文件对象 upload_file
 *
 * @author flyhigh
 * @date 2022-12-29
 */
@Data
@TableName("upload_file")
public class UploadFile {

    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文件名
     */
    @TableField(value = "file_name")
    private String fileName;

    /**
     * 原名
     */
    @TableField(value = "original_name")
    private String originalName;

    /**
     * 文件类型
     */
    @TableField(value = "file_type")
    private String fileType;

    /**
     * 关联id
     */
    @TableField(value = "relation_id")
    private Long relationId;

    /**
     * 文件后缀名
     */
    @TableField(value = "file_suffix")
    private String fileSuffix;

    /**
     * URL地址
     */
    @TableField(value = "url")
    private String url;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 上传人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 短链接
     */
    @TableField(exist = false)
    private String shortUrl;

}
