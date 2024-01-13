package com.flyhigh.form.domain.convert;

import com.flyhigh.api.vo.form.FormVO;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.form.domain.entity.Form;
import com.flyhigh.api.vo.form.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 单转换对象表单表 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface FormConvert {

    FormConvert INSTANCE = Mappers.getMapper(FormConvert.class);

    Form convert(FormCreateReq bean);

    Form convert(AddUserFormCreateReq bean);

    Form convert(FormUpdateReq bean);

    Form convert(FormExportReq bean);

    FormVO convert(Form bean);

    List<FormVO> convertList(List<Form> list);

    TableDataInfo<FormVO> convertPage(TableDataInfo<Form> page);

    List<FormExcelVO> convertListExcel(List<Form> list);

}
