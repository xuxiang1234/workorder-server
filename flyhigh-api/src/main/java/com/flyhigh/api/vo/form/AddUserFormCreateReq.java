package com.flyhigh.api.vo.form;

import com.flyhigh.api.enums.SystemIdentification;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel("单新增接口 Request")
@Data
@ToString(callSuper = true)
public class AddUserFormCreateReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表单仓库ID", required = true)
    @NotNull(message = "表单仓库ID")
    private Long formWarehouseId;

    @ApiModelProperty(value = "关联ID", required = true)
    @NotNull(message = "关联ID不能为空")
    private Long contactId;

    @ApiModelProperty(value = "系统 保证每个系统数据不冲突", required = true)
    @NotNull(message = "系统 保证每个系统数据不冲突不能为空")
    private SystemIdentification system;

}
