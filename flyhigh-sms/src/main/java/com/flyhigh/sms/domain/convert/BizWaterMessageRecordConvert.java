package com.flyhigh.sms.domain.convert;

import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.sms.domain.BizWaterMessageRecord;
import com.flyhigh.sms.domain.vo.waterMessageRecord.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 业务消息流水转换对象消息业务流水记录 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface BizWaterMessageRecordConvert {

    BizWaterMessageRecordConvert INSTANCE = Mappers.getMapper(BizWaterMessageRecordConvert.class);

    BizWaterMessageRecord convert(BizWaterMessageRecordCreateReq bean);

    BizWaterMessageRecord convert(BizWaterMessageRecordUpdateReq bean);

    BizWaterMessageRecord convert(BizWaterMessageRecordExportReq bean);

    BizWaterMessageRecordVO convert(BizWaterMessageRecord bean);

    List<BizWaterMessageRecordVO> convertList(List<BizWaterMessageRecord> list);

    TableDataInfo<BizWaterMessageRecordVO> convertPage(TableDataInfo<BizWaterMessageRecord> page);

    List<BizWaterMessageRecordExcelVO> convertListExcel(List<BizWaterMessageRecord> list);

}
