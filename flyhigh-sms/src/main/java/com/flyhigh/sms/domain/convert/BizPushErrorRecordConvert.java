package com.flyhigh.sms.domain.convert;

import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.sms.domain.BizPushErrorRecord;
import com.flyhigh.sms.domain.vo.pushErrorRecord.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 推送异常转换对象推送异常表 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface BizPushErrorRecordConvert {

    BizPushErrorRecordConvert INSTANCE = Mappers.getMapper(BizPushErrorRecordConvert.class);

    BizPushErrorRecord convert(BizPushErrorRecordCreateReq bean);

    BizPushErrorRecord convert(BizPushErrorRecordUpdateReq bean);

    BizPushErrorRecord convert(BizPushErrorRecordExportReq bean);

    BizPushErrorRecordVO convert(BizPushErrorRecord bean);

    List<BizPushErrorRecordVO> convertList(List<BizPushErrorRecord> list);

    TableDataInfo<BizPushErrorRecordVO> convertPage(TableDataInfo<BizPushErrorRecord> page);

    List<BizPushErrorRecordExcelVO> convertListExcel(List<BizPushErrorRecord> list);

}
