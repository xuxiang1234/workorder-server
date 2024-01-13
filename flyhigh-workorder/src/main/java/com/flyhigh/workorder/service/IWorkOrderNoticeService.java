package com.flyhigh.workorder.service;

import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.workorder.domain.entity.WorkOrderProcess;
import com.flyhigh.workorder.domain.vo.workordernotice.WorkOrderNoticeExcelVO;
import com.flyhigh.workorder.domain.vo.workordernotice.WorkOrderNoticeExportReq;
import com.flyhigh.workorder.domain.vo.workordernotice.WorkOrderNoticePageReq;
import com.flyhigh.workorder.domain.vo.workordernotice.WorkOrderNoticeVO;

import java.util.List;

/**
 * 工单消息通知Service接口
 *
 * @author flyhigh
 * @date 2022-05-21
 */
public interface IWorkOrderNoticeService {

    /**
     * 查询工单消息通知
     *
     * @param id 工单消息通知主键
     * @return 工单消息通知
     */
    WorkOrderNoticeVO queryById(Long id);

    /**
     * 查询工单消息通知列表
     *
     * @param pageVO 工单消息通知
     * @return 工单消息通知集合
     */
    TableDataInfo<WorkOrderNoticeVO> queryPageList(WorkOrderNoticePageReq pageVO);

    /**
     * 查询工单消息通知列表
     *
     * @param exportReq 工单消息通知
     * @return 工单消息通知集合
     */
    List<WorkOrderNoticeExcelVO> queryList(WorkOrderNoticeExportReq exportReq);

    /**
     * 根据流程通知具体的人
     *
     * @param workOrderProcess 流程
     */
    void noticeByProcess(WorkOrderProcess workOrderProcess);

    /**
     * 工单消息设置已读
     *
     * @param id
     * @return
     */
    Boolean noticeRead(Long id);

}
