package com.flyhigh.workorder.domain.convert;

import com.flyhigh.api.vo.workorderprocess.WorkOrderProcessHistoryVO;
import com.flyhigh.api.vo.workorderprocess.WorkOrderProcessVO;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.workorder.domain.entity.TemplateProcess;
import com.flyhigh.workorder.domain.entity.WorkOrderProcess;
import com.flyhigh.workorder.domain.vo.workorder.WorkOrderHandleReq;
import com.flyhigh.workorder.domain.vo.workorderprocess.WorkOrderProcessCreateReq;
import com.flyhigh.workorder.domain.vo.workorderprocess.WorkOrderProcessExcelVO;
import com.flyhigh.workorder.domain.vo.workorderprocess.WorkOrderProcessExportReq;
import com.flyhigh.workorder.domain.vo.workorderprocess.WorkOrderProcessUpdateReq;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 工单流程转换对象工单流程表 Convert
 *
 * @author flyhigh
 */
@Mapper
public interface WorkOrderProcessConvert {

    WorkOrderProcessConvert INSTANCE = Mappers.getMapper(WorkOrderProcessConvert.class);

    WorkOrderProcess convert(WorkOrderProcessCreateReq bean);

    WorkOrderProcess convert(WorkOrderProcessUpdateReq bean);

    WorkOrderProcess convert(WorkOrderProcessExportReq bean);

    WorkOrderProcessVO convert(WorkOrderProcess bean);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "processIndex", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "evaluate", ignore = true)
    @Mapping(target = "evaluatePicUrl", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    WorkOrderProcess convertThis(WorkOrderProcess bean);


    @Mapping(target = "status", ignore = true)
    @Mapping(target = "evaluate", ignore = true)
    @Mapping(target = "evaluatePicUrl", ignore = true)
    WorkOrderProcess convertAdd(WorkOrderHandleReq bean);

    List<WorkOrderProcessVO> convertList(List<WorkOrderProcess> list);

    List<WorkOrderProcessHistoryVO> convertHistoryList(List<WorkOrderProcess> list);

    TableDataInfo<WorkOrderProcessVO> convertPage(TableDataInfo<WorkOrderProcess> page);

    List<WorkOrderProcessExcelVO> convertListExcel(List<WorkOrderProcess> list);


    @IterableMapping(qualifiedByName = "templateProcessConvert")
    List<WorkOrderProcess> templateProcessConvertList(List<TemplateProcess> list);

    @Named("templateProcessConvert")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    WorkOrderProcess templateProcessConvert(TemplateProcess templateProcess);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    WorkOrderProcess templateProcessConvert(WorkOrderProcess workOrderProcess);

}
