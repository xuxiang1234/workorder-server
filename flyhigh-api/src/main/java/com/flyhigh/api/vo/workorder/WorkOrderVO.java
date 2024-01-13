package com.flyhigh.api.vo.workorder;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.flyhigh.api.vo.workorderprocess.WorkOrderProcessVO;
import com.flyhigh.common.annotation.ExcelDictFormat;
import com.flyhigh.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 工单视图对象 work_order
 *
 * @author flyhigh
 * @date 2022-05-17
 */
@Data
@ApiModel("工单视图对象")
@ExcelIgnoreUnannotated
public class WorkOrderVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "ID")
    @ApiModelProperty("ID")
    private Long id;

    /**
     * 工单名称
     */
    @ExcelProperty(value = "工单名称")
    @ApiModelProperty("工单名称")
    private String workOrderName;

    /**
     * 工单类型
     */
    @ExcelProperty(value = "工单类型")
    @ApiModelProperty("工单类型")
    private String workOrderType;

    /**
     * 工单等级
     */
    @ExcelProperty(value = "工单等级")
    @ApiModelProperty("工单等级")
    private String workOrderLevel;

    /**
     * 预计开始时间
     */
    @ApiModelProperty("预计开始时间")
    private Date startTime;

    /**
     * 设备名称
     */
    @ExcelProperty(value = "设备名称")
    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    /**
     * 位置
     */
    @ExcelProperty(value = "位置")
    @ApiModelProperty(value = "位置")
    private String position;

    /**
     * 来源
     */
    @ExcelProperty(value = "来源")
    @ApiModelProperty(value = "来源")
    private String source;

    /**
     * 工单描述
     */
    @ExcelProperty(value = "工单描述")
    @ApiModelProperty("工单描述")
    private String description;

    /**
     * 工单图片地址 多个逗号分隔
     */
    @ExcelProperty(value = "工单图片地址 多个逗号分隔")
    @ApiModelProperty("工单图片地址 多个逗号分隔")
    private String picUrl;

    /**
     * 工单状态 未完成 已完成
     */
    @ExcelProperty(value = "工单状态 未完成 已完成")
    @ApiModelProperty("工单状态 未完成 已完成")
    private String status;

    /**
     * 是否驳回
     */
    @ExcelProperty(value = "是否驳回")
    @ApiModelProperty("是否驳回")
    private Integer isReject;

    /**
     * 评价
     */
    @ExcelProperty(value = "评价")
    @ApiModelProperty("评价")
    private String evaluate;

    /**
     * 评分
     */
    @ExcelProperty(value = "评分")
    @ApiModelProperty("评分")
    private Integer score;

    /**
     * 报价"
     */
    @ExcelProperty(value = "报价")
    @ApiModelProperty("报价")
    private String offer;

    /**
     * 科室
     */
    @ExcelProperty(value = "科室")
    @ApiModelProperty("科室")
    private String office;

    /**
     * 报修人
     */
    @ExcelProperty(value = "报修人")
    @ApiModelProperty("报修人")
    private String repairName;

    /**
     * 报修人ID
     */
    @ExcelProperty(value = "报修人ID")
    @ApiModelProperty("报修人ID")
    private String repairId;

    /**
     * 完成时间
     */
    @ExcelProperty(value = "完成时间")
    @ApiModelProperty("完成时间")
    private Date completeTime;

    /**
     * 创建人ID
     */
    @ExcelProperty(value = "创建人ID")
    @ApiModelProperty("创建人ID")
    private Long createId;

    /**
     * 创建人姓名
     */
    @ExcelProperty(value = "创建人姓名")
    @ApiModelProperty("创建人姓名")
    private String createName;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 模板编号
     */
    @ApiModelProperty("模板编号")
    private Long templateId;


    /**
     * 工单流程
     */
    @ExcelProperty(value = "工单流程")
    @ApiModelProperty("工单流程")
    private List<WorkOrderProcessVO> workOrderProcessVOList;

}
