package com.flyhigh.sms.domain.convert;

import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.sms.domain.BizMessageInfo;
import com.flyhigh.api.dto.messageInfo.BizMessageInfoCreateDTO;
import com.flyhigh.sms.domain.vo.messageInfo.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 消息转换对象消息 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface BizMessageInfoConvert {

    BizMessageInfoConvert INSTANCE = Mappers.getMapper(BizMessageInfoConvert.class);

    @Mapping(target = "createTime", expression = "java(new java.util.Date())")
    @Mapping(target = "updateBy", expression = "java(com.flyhigh.common.utils.SecurityUtils.getUsernameIfPresent())")
    BizMessageInfo convert(BizMessageInfoCreateReq bean);

    @Mapping(target = "updateBy", expression = "java(com.flyhigh.common.utils.SecurityUtils.getUsername())")
    @Mapping(target = "updateTime", expression = "java(new java.util.Date())")
    BizMessageInfo convert(BizMessageInfoUpdateReq bean);

    @Mapping(target = "createTime", expression = "java(new java.util.Date())")
    @Mapping(target = "updateBy", expression = "java(com.flyhigh.common.utils.SecurityUtils.getUsername())")
    BizMessageInfo convert(BizMessageInfoCreateDTO bizMessageInfoCreateDTO);

    BizMessageInfo convert(BizMessageInfoExportReq bean);

    BizMessageInfoVO convert(BizMessageInfo bean);

    List<BizMessageInfoVO> convertList(List<BizMessageInfo> list);

    TableDataInfo<BizMessageInfoVO> convertPage(TableDataInfo<BizMessageInfo> page);

    List<BizMessageInfoExcelVO> convertListExcel(List<BizMessageInfo> list);

}
