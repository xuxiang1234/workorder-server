package com.flyhigh.api.vo.formWarehouse;

import com.flyhigh.common.core.domain.PageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 评分表单分页
 *
 * @author Mr.Lai
 * @date 2023/5/5
 */
@Data
public class ScoreFormPageReq extends PageQuery {

    /***
     * 表单类别 LEASE_UNIT_SCORE_FORM 租赁单元、TENANT_SCORE_FORM 入驻商家
     */
    @ApiModelProperty(value = "表单类别")
    private String category;

}
