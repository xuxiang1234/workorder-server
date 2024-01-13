package com.flyhigh.workorder.domain.convert;

import com.flyhigh.api.vo.template.TemplatePageReq;
import com.flyhigh.api.vo.template.TemplateSimplifyVO;
import com.flyhigh.api.vo.template.TemplateVO;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.workorder.domain.entity.Template;
import com.flyhigh.workorder.domain.vo.template.TemplateCreateReq;
import com.flyhigh.workorder.domain.vo.template.TemplateExcelVO;
import com.flyhigh.workorder.domain.vo.template.TemplateExportReq;
import com.flyhigh.workorder.domain.vo.template.TemplateUpdateReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 模板转换对象模板表 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface TemplateConvert {

    TemplateConvert INSTANCE = Mappers.getMapper(TemplateConvert.class);

    Template convert(TemplateCreateReq bean);

    Template convert(TemplatePageReq pageReq);

    Template convert(TemplateUpdateReq bean);

    Template convert(TemplateExportReq bean);

    TemplateVO convert(Template bean);

    List<TemplateVO> convertList(List<Template> list);

    List<TemplateSimplifyVO> convertSimplifyList(List<Template> list);

    TableDataInfo<TemplateVO> convertPage(TableDataInfo<Template> page);

    List<TemplateExcelVO> convertListExcel(List<Template> list);

}
