package com.flyhigh.workorder.domain.convert;

import com.flyhigh.api.vo.templateprocess.TemplateProcessVO;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.workorder.domain.entity.TemplateProcess;
import com.flyhigh.workorder.domain.vo.templateprocess.TemplateProcessCreateReq;
import com.flyhigh.workorder.domain.vo.templateprocess.TemplateProcessExcelVO;
import com.flyhigh.workorder.domain.vo.templateprocess.TemplateProcessExportReq;
import com.flyhigh.workorder.domain.vo.templateprocess.TemplateProcessUpdateReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 模板流程转换对象模板流程表 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface TemplateProcessConvert {

    TemplateProcessConvert INSTANCE = Mappers.getMapper(TemplateProcessConvert.class);

    TemplateProcess convert(TemplateProcessCreateReq bean);

    TemplateProcess convert(TemplateProcessUpdateReq bean);

    TemplateProcess convert(TemplateProcessExportReq bean);

    TemplateProcessVO convert(TemplateProcess bean);

    List<TemplateProcessVO> convertList(List<TemplateProcess> list);

    TableDataInfo<TemplateProcessVO> convertPage(TableDataInfo<TemplateProcess> page);

    List<TemplateProcessExcelVO> convertListExcel(List<TemplateProcess> list);

    List<TemplateProcess> convertCreateReqList(List<TemplateProcessCreateReq> list);

    List<TemplateProcess> convertUpdateReqList(List<TemplateProcessUpdateReq> list);

}
