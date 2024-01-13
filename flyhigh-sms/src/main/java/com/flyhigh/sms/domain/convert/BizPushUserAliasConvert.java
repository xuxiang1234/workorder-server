package com.flyhigh.sms.domain.convert;

import com.flyhigh.api.dto.smsPush.BizPushBindReq;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.sms.domain.BizPushUserAlias;
import com.flyhigh.sms.domain.vo.pushUserAlias.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户个推别名转换对象用户个推别名表 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface BizPushUserAliasConvert {

    BizPushUserAliasConvert INSTANCE = Mappers.getMapper(BizPushUserAliasConvert.class);

    BizPushUserAlias convert(BizPushUserAliasCreateReq bean);

    BizPushUserAlias convert(BizPushUserAliasUpdateReq bean);

    BizPushUserAlias convert(BizPushUserAliasExportReq bean);

    BizPushUserAliasVO convert(BizPushUserAlias bean);

    List<BizPushUserAliasVO> convertList(List<BizPushUserAlias> list);

    TableDataInfo<BizPushUserAliasVO> convertPage(TableDataInfo<BizPushUserAlias> page);

    List<BizPushUserAliasExcelVO> convertListExcel(List<BizPushUserAlias> list);


    @Mapping(target = "createTime", expression = "java(new java.util.Date())")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "userAlias", expression = "java(java.lang.String.valueOf(userId))")
    BizPushUserAlias convert(BizPushBindReq pushBindReq, Long userId);

}
