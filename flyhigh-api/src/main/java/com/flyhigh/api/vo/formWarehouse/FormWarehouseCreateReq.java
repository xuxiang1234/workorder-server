package com.flyhigh.api.vo.formWarehouse;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("表表单仓库新增接口 Request")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FormWarehouseCreateReq extends FormWarehouseBaseReq {

}
