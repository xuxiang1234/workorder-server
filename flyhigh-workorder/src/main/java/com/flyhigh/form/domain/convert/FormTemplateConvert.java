package com.flyhigh.form.domain.convert;

import com.flyhigh.api.vo.formtemplate.*;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.form.domain.entity.FormTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 单模板转换对象表单模板表 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface FormTemplateConvert {

    FormTemplateConvert INSTANCE = Mappers.getMapper(FormTemplateConvert.class);

    FormTemplate convert(FormTemplateCreateReq bean);

    List<FormTemplate> convert(List<FormTemplateCreateReq> bean);

    FormTemplate convert(FormTemplateUpdateReq bean);

    List<FormTemplate> convertUpdateList(List<FormTemplateUpdateReq> bean);

    FormTemplate convert(FormTemplateExportReq bean);

    FormTemplateVO convert(FormTemplate bean);

    List<FormTemplateVO> convertList(List<FormTemplate> list);

    TableDataInfo<FormTemplateVO> convertPage(TableDataInfo<FormTemplate> page);

    List<FormTemplateExcelVO> convertListExcel(List<FormTemplate> list);

}
