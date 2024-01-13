package com.flyhigh.sms.domain.convert;

import com.flyhigh.api.dto.messageInfo.BizMessageInfoCreateDTO;
import com.flyhigh.api.dto.messageInfo.BizMessageInfoUpdateDTO;
import com.flyhigh.sms.domain.vo.messageInfo.BizMessageInfoCreateReq;
import com.flyhigh.sms.domain.vo.messageInfo.BizMessageInfoUpdateReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * 流程主表转换对象流程表 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface BizMessageInfoCreateConvert {

    BizMessageInfoCreateConvert INSTANCE = Mappers.getMapper(BizMessageInfoCreateConvert.class);

    BizMessageInfoCreateReq convert(BizMessageInfoCreateDTO bean);

    BizMessageInfoUpdateReq convert(BizMessageInfoUpdateDTO bean);

}
