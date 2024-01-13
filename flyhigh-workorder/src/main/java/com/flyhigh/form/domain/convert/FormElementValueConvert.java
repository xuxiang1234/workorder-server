package com.flyhigh.form.domain.convert;

import com.flyhigh.api.vo.elementvalue.*;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.form.domain.entity.FormElementValue;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 表单元素值转换对象表单元素值表 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface FormElementValueConvert {

    FormElementValueConvert INSTANCE = Mappers.getMapper(FormElementValueConvert.class);

    FormElementValue convert(FormElementValueCreateReq bean);

    FormElementValue convert(FormElementValueUpdateReq bean);

    FormElementValue convert(FormElementValueExportReq bean);

    FormElementValueVO convert(FormElementValue bean);

    List<FormElementValueVO> convertList(List<FormElementValue> list);

    TableDataInfo<FormElementValueVO> convertPage(TableDataInfo<FormElementValue> page);

    List<FormElementValueExcelVO> convertListExcel(List<FormElementValue> list);

}
