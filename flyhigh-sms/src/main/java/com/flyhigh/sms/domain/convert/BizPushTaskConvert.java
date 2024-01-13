package com.flyhigh.sms.domain.convert;

import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.sms.domain.BizPushTask;
import com.flyhigh.sms.domain.vo.pushTask.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 推送任务转换对象推送任务表 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface BizPushTaskConvert {

    BizPushTaskConvert INSTANCE = Mappers.getMapper(BizPushTaskConvert.class);

    BizPushTask convert(BizPushTaskCreateReq bean);

    BizPushTask convert(BizPushTaskUpdateReq bean);

    BizPushTask convert(BizPushTaskExportReq bean);

    BizPushTaskVO convert(BizPushTask bean);

    List<BizPushTaskVO> convertList(List<BizPushTask> list);

    TableDataInfo<BizPushTaskVO> convertPage(TableDataInfo<BizPushTask> page);

    List<BizPushTaskExcelVO> convertListExcel(List<BizPushTask> list);

}
