package com.flyhigh.workorder.domain.convert;

import com.flyhigh.api.vo.workorder.WorkOrderCreateReq;
import com.flyhigh.api.vo.workorder.WorkOrderExcelVO;
import com.flyhigh.api.vo.workorder.WorkOrderExportReq;
import com.flyhigh.api.vo.workorder.WorkOrderVO;
import com.flyhigh.workorder.domain.entity.WorkOrder;
import com.flyhigh.api.vo.workorder.WorkOrderUpdateReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 工单转换对象工单表 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface WorkOrderConvert {

    WorkOrderConvert INSTANCE = Mappers.getMapper(WorkOrderConvert.class);

    WorkOrder convert(WorkOrderCreateReq bean);

    WorkOrder convert(WorkOrderUpdateReq bean);

    WorkOrder convert(WorkOrderExportReq bean);

    WorkOrderVO convert(WorkOrder bean);

    List<WorkOrderVO> convertList(List<WorkOrder> list);

    List<WorkOrderExcelVO> convertListExcel(List<WorkOrder> list);

    List<WorkOrderVO> convertListExcelVO(List<WorkOrder> list);

}
