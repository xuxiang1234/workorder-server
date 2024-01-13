package com.flyhigh.sms.domain.convert;

import com.flyhigh.api.dto.smsPush.BizPushBindReq;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.sms.domain.BizPushUserCid;
import com.flyhigh.sms.domain.vo.pushUserCid.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户个推cid关系绑定转换对象用户个推cid关系绑定表 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface BizPushUserCidConvert {

    BizPushUserCidConvert INSTANCE = Mappers.getMapper(BizPushUserCidConvert.class);

    BizPushUserCid convert(BizPushUserCidCreateReq bean);

    BizPushUserCid convert(BizPushUserCidUpdateReq bean);

    BizPushUserCid convert(BizPushUserCidExportReq bean);

    BizPushUserCidVO convert(BizPushUserCid bean);

    List<BizPushUserCidVO> convertList(List<BizPushUserCid> list);

    TableDataInfo<BizPushUserCidVO> convertPage(TableDataInfo<BizPushUserCid> page);

    List<BizPushUserCidExcelVO> convertListExcel(List<BizPushUserCid> list);

    @Mapping(target = "createTime", expression = "java(new java.util.Date())")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "cid", source = "pushBindReq.cid")
    @Mapping(target = "userAlias", expression = "java(java.lang.String.valueOf(userId))")
    BizPushUserCid convert(BizPushBindReq pushBindReq, Long userId);

}
