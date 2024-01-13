package com.flyhigh.form.domain.convert;

import com.flyhigh.api.vo.element.*;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.form.domain.entity.FormElement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 表单元素转换对象表单元素表 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface FormElementConvert {

    FormElementConvert INSTANCE = Mappers.getMapper(FormElementConvert.class);

    FormElement convert(FormElementCreateReq bean);

    FormElement convert(FormElementUpdateReq bean);

    FormElement convert(FormElementExportReq bean);

    FormElementVO convert(FormElement bean);

    List<FormElementVO> convertList(List<FormElement> list);

    TableDataInfo<FormElementVO> convertPage(TableDataInfo<FormElement> page);

    List<FormElementExcelVO> convertListExcel(List<FormElement> list);

}
