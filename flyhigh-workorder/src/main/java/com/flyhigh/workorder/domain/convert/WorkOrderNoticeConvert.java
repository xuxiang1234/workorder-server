package com.flyhigh.workorder.domain.convert;

import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.workorder.domain.entity.WorkOrderNotice;
import com.flyhigh.workorder.domain.vo.workordernotice.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 工单消息通知转换对象工单消息通知表 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface WorkOrderNoticeConvert {

    WorkOrderNoticeConvert INSTANCE = Mappers.getMapper(WorkOrderNoticeConvert.class);

    WorkOrderNotice convert(WorkOrderNoticeCreateReq bean);

    WorkOrderNotice convert(WorkOrderNoticeUpdateReq bean);

    WorkOrderNotice convert(WorkOrderNoticeExportReq bean);

    WorkOrderNoticeVO convert(WorkOrderNotice bean);

    List<WorkOrderNoticeVO> convertList(List<WorkOrderNotice> list);

    TableDataInfo<WorkOrderNoticeVO> convertPage(TableDataInfo<WorkOrderNotice> page);

    List<WorkOrderNoticeExcelVO> convertListExcel(List<WorkOrderNotice> list);

}
