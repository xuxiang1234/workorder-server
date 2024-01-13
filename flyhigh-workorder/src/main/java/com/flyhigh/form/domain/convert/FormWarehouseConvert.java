package com.flyhigh.form.domain.convert;


import com.flyhigh.api.vo.formWarehouse.*;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.form.domain.entity.FormWarehouse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * 表表单仓库转换对象表表表单仓库表 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface FormWarehouseConvert {

    FormWarehouseConvert INSTANCE = Mappers.getMapper(FormWarehouseConvert.class);

    FormWarehouse convert(FormWarehouseCreateReq bean);

    FormWarehouse convert(FormWarehouseUpdateReq bean);

    FormWarehouse convert(FormWarehouseExportReq bean);

    FormWarehouseVO convert(FormWarehouse bean);

    List<FormWarehouseVO> convertList(List<FormWarehouse> list);

    TableDataInfo<FormWarehouseVO> convertPage(TableDataInfo<FormWarehouse> page);

    List<FormWarehouseExcelVO> convertListExcel(List<FormWarehouse> list);

}
