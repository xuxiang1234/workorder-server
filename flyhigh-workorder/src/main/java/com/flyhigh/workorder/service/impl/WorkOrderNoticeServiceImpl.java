package com.flyhigh.workorder.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.flyhigh.common.constant.HttpStatus;
import com.flyhigh.common.core.domain.model.LoginUser;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.exception.ErrorCodeConstants;
import com.flyhigh.common.exception.ServiceExceptionUtil;
import com.flyhigh.common.utils.SecurityUtils;
import com.flyhigh.workorder.domain.convert.WorkOrderNoticeConvert;
import com.flyhigh.workorder.domain.entity.WorkOrder;
import com.flyhigh.workorder.domain.entity.WorkOrderNotice;
import com.flyhigh.workorder.domain.entity.WorkOrderProcess;
import com.flyhigh.workorder.domain.vo.workordernotice.WorkOrderNoticeExcelVO;
import com.flyhigh.workorder.domain.vo.workordernotice.WorkOrderNoticeExportReq;
import com.flyhigh.workorder.domain.vo.workordernotice.WorkOrderNoticePageReq;
import com.flyhigh.workorder.domain.vo.workordernotice.WorkOrderNoticeVO;
import com.flyhigh.workorder.mapper.WorkOrderMapper;
import com.flyhigh.workorder.mapper.WorkOrderNoticeMapper;
import com.flyhigh.workorder.mapper.WorkOrderProcessMapper;
import com.flyhigh.workorder.service.IWorkOrderNoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 工单消息通知Service业务层处理
 *
 * @author flyhigh
 * @date 2022-05-21
 */
@Service
@Validated
@RequiredArgsConstructor
public class WorkOrderNoticeServiceImpl implements IWorkOrderNoticeService {

    private final WorkOrderNoticeMapper workOrderNoticeMapper;

    private final WorkOrderMapper workOrderMapper;

    /**
     * 校验
     */
    private void validateWorkOrderNotice(WorkOrderNotice workOrderNotice) {
        //是否存在
        if (null == workOrderNotice) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }

        //是否是本人的消息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (!loginUser.getUserId().equals(workOrderNotice.getUserId())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }
    }

    /**
     * 查询工单消息通知
     *
     * @param id 工单消息通知主键
     * @return 工单消息通知
     */
    @Override
    public WorkOrderNoticeVO queryById(Long id) {
        WorkOrderNotice workOrderNotice = workOrderNoticeMapper.selectById(id);
        validateWorkOrderNotice(workOrderNotice);
        WorkOrderNoticeVO workOrderNoticeVO = WorkOrderNoticeConvert.INSTANCE.convert(workOrderNotice);
        WorkOrder workOrder = workOrderMapper.selectById(workOrderNotice.getWorkOrderId());
        if(null != workOrder) {
            workOrderNoticeVO.setWorkOrderType(workOrder.getWorkOrderType());
        }
        return workOrderNoticeVO;
    }

    /**
     * 查询工单消息通知列表
     *
     * @param pageReq 工单消息通知
     * @return 工单消息通知
     */
    @Override
    public TableDataInfo<WorkOrderNoticeVO> queryPageList(WorkOrderNoticePageReq pageReq) {
        if (ObjectUtils.isNotNull(pageReq.getPageNum(), pageReq.getPageSize())) {
            PageHelper.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        }
        pageReq.setUserId(SecurityUtils.getUserId());
        PageInfo<WorkOrderNotice> pageInfo = new PageInfo<>(workOrderNoticeMapper.selectList(pageReq));
        TableDataInfo<WorkOrderNotice> tableDataInfo = new TableDataInfo<>(pageInfo.getList(), new Long(pageInfo.getTotal()).intValue());
        TableDataInfo<WorkOrderNoticeVO> noticeVOTableDataInfo = WorkOrderNoticeConvert.INSTANCE.convertPage(tableDataInfo);
        noticeVOTableDataInfo.setCode(HttpStatus.SUCCESS);
        noticeVOTableDataInfo.setMsg("查询成功");
        return noticeVOTableDataInfo;
    }

    /**
     * 查询工单消息通知列表
     *
     * @param exportReq 工单消息通知
     * @return 工单消息通知
     */
    @Override
    public List<WorkOrderNoticeExcelVO> queryList(WorkOrderNoticeExportReq exportReq) {
        List<WorkOrderNotice> workOrderNoticeLists = workOrderNoticeMapper.selectList(exportReq);
        return WorkOrderNoticeConvert.INSTANCE.convertListExcel(workOrderNoticeLists);
    }


    /**
     * 根据流程通知具体的人
     *
     * @param workOrderProcess 流程
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void noticeByProcess(WorkOrderProcess workOrderProcess) {
        if (null == workOrderProcess) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }

        //小于0的账号不推送
        if (workOrderProcess.getUserId() < 0) {
            return;
        }

        //查询工单数据
        WorkOrder workOrder = workOrderMapper.selectById(workOrderProcess.getWorkOrderId());

        //添加消息数据
        WorkOrderNotice workOrderNotice = new WorkOrderNotice();
        workOrderNotice.setWorkOrderId(workOrderProcess.getWorkOrderId());
        workOrderNotice.setWorkOrderName(workOrder.getWorkOrderName());
        workOrderNotice.setUserId(workOrderProcess.getUserId());
        workOrderNoticeMapper.insert(workOrderNotice);
    }


    /**
     * 工单消息设置已读
     *
     * @param id
     * @return
     */
    @Override
    public Boolean noticeRead(Long id) {
        WorkOrderNotice workOrderNotice = workOrderNoticeMapper.selectById(id);
        validateWorkOrderNotice(workOrderNotice);

        workOrderNotice.setIsRead(1);
        workOrderNoticeMapper.updateById(workOrderNotice);

        return true;
    }

}
