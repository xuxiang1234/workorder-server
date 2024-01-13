package com.flyhigh.workorder.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyhigh.api.dto.messageInfo.BizMessageInfoCreateDTO;
import com.flyhigh.api.enums.PlatformEnum;
import com.flyhigh.api.service.MessageBizServiceApi;
import com.flyhigh.api.vo.workorder.*;
import com.flyhigh.api.vo.workorderprocess.WorkOrderProcessHistoryVO;
import com.flyhigh.api.vo.workorderprocess.WorkOrderProcessVO;
import com.flyhigh.common.core.domain.entity.SysDept;
import com.flyhigh.common.core.domain.entity.SysUser;
import com.flyhigh.common.core.domain.model.LoginUser;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.MessageTemplateEnum;
import com.flyhigh.common.exception.ErrorCodeConstants;
import com.flyhigh.common.exception.GlobalErrorCodeConstants;
import com.flyhigh.common.exception.ServiceExceptionUtil;
import com.flyhigh.common.utils.DateUtils;
import com.flyhigh.common.utils.SecurityUtils;
import com.flyhigh.form.domain.enums.FormBizField;
import com.flyhigh.form.domain.enums.FormBizName;
import com.flyhigh.api.enums.SystemIdentification;
import com.flyhigh.api.vo.element.FormElementVO;
import com.flyhigh.api.vo.form.AddUserFormCreateReq;
import com.flyhigh.api.vo.form.FormCreateReq;
import com.flyhigh.form.service.IFormService;
import com.flyhigh.system.mapper.SysDeptMapper;
import com.flyhigh.system.service.ISysUserService;
import com.flyhigh.workorder.domain.convert.WorkOrderConvert;
import com.flyhigh.workorder.domain.convert.WorkOrderProcessConvert;
import com.flyhigh.workorder.domain.entity.TemplateProcess;
import com.flyhigh.workorder.domain.entity.WorkOrder;
import com.flyhigh.workorder.domain.entity.WorkOrderProcess;
import com.flyhigh.workorder.domain.enums.ProcessStatus;
import com.flyhigh.workorder.domain.enums.ProcessType;
import com.flyhigh.api.enums.WorkOrderStatus;
import com.flyhigh.api.vo.workorder.WorkOrderEvaluateReq;
import com.flyhigh.workorder.domain.vo.workorder.WorkOrderEvaluateVO;
import com.flyhigh.workorder.domain.vo.workorder.WorkOrderHandleReq;
import com.flyhigh.workorder.domain.vo.workorderprocess.ProcessHistoryStatusVO;
import com.flyhigh.workorder.mapper.WorkOrderMapper;
import com.flyhigh.workorder.mapper.WorkOrderProcessMapper;
import com.flyhigh.workorder.producer.WorkOrderProducer;
import com.flyhigh.workorder.service.ITemplateService;
import com.flyhigh.workorder.service.IWorkOrderNoticeService;
import com.flyhigh.workorder.service.IWorkOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 工单Service业务层处理
 *
 * @author flyhigh
 * @date 2022-05-17
 */
@Service
@Validated
@RequiredArgsConstructor
public class WorkOrderServiceImpl implements IWorkOrderService {

    private final WorkOrderMapper workOrderMapper;

    private final WorkOrderProcessMapper workOrderProcessMapper;

    private final ITemplateService itemplateService;

    private final IWorkOrderNoticeService workOrderNoticeService;

    private final IFormService formService;

    private final SysDeptMapper sysDeptMapper;

    private final ISysUserService userService;

    private final WorkOrderProducer workOrderProducer;

    private final MessageBizServiceApi messageBizServiceApi;

    /**
     * 校验工单是否存在
     */
    private void validateWorkOrderExists(Long id) {
        if (workOrderMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }
    }

    /**
     * 查询工单
     *
     * @param id 工单主键
     * @return 工单
     */
    @Override
    public WorkOrderVO queryById(Long id) {
        // 校验存在
        validateWorkOrderExists(id);

        WorkOrder workOrder = workOrderMapper.selectById(id);
        WorkOrderVO workOrderVO = WorkOrderConvert.INSTANCE.convert(workOrder);

        //查询工单流程
        List<WorkOrderProcess> workOrderProcessList = queryWorkOrderProcessList(id);
        //过滤掉未执行的流程
        workOrderProcessList = workOrderProcessList.stream().filter(e -> StringUtils.isNotBlank(e.getStatus())).collect(Collectors.toList());

        List<WorkOrderProcessVO> workOrderProcessVOList = WorkOrderProcessConvert.INSTANCE.convertList(workOrderProcessList);

        //查询是否包含表单
        List<Long> existIds = formService.queryFormExist(workOrderProcessVOList.stream().map(e -> e.getId()).collect(Collectors.toList()), SystemIdentification.WORK_ORDER_SYSTEM);
        workOrderProcessVOList.forEach(e -> {
            if (existIds.contains(e.getId())) {
                e.setIsExistForm(1);
            }
        });

        workOrderVO.setWorkOrderProcessVOList(workOrderProcessVOList);
        return workOrderVO;
    }

    /**
     * 查询运维工单列表
     *
     * @param exportReq 运维工单
     * @return 运维工单
     */
    @Override
    public List<WorkOrderVO> queryListVO(WorkOrderExportReq exportReq) {
        List<WorkOrder> workOrderLists = workOrderMapper.selectList(exportReq);
        return WorkOrderConvert.INSTANCE.convertListExcelVO(workOrderLists);
    }

    /**
     * 查询运维工单列表
     *
     * @param exportReq 运维工单
     * @return 运维工单
     */
    @Override
    public List<WorkOrderExcelVO> queryList(WorkOrderExportReq exportReq) {
        List<WorkOrder> workOrderLists = workOrderMapper.selectList(exportReq);
        return WorkOrderConvert.INSTANCE.convertListExcel(workOrderLists);
    }

    /**
     * 获取工单历史
     *
     * @param id
     * @return
     */
    @Override
    public List<WorkOrderProcessHistoryVO> getHistoryInfo(Long id) {
        // 校验存在
        validateWorkOrderExists(id);

        WorkOrder workOrder = workOrderMapper.selectById(id);

        //查询工单流程
        List<WorkOrderProcess> workOrderProcessList = queryWorkOrderProcessList(id);
        //过滤掉未执行的流程
        workOrderProcessList = workOrderProcessList.stream().filter(e -> StringUtils.isNotBlank(e.getStatus())).collect(Collectors.toList());
        List<WorkOrderProcessHistoryVO> workOrderProcessVOList = WorkOrderProcessConvert.INSTANCE.convertHistoryList(workOrderProcessList);

        //处理成前端需要的数据
        for (int i = 0; i < workOrderProcessVOList.size(); i++) {

            // 判断是否最后流程
            if (workOrderProcessVOList.get(i).getIsLast().equals(0)) {

                if (!workOrderProcessVOList.get(i).getStatus().equals(ProcessStatus.NOW.name())) {
                    workOrderProcessVOList.get(i).setNextUserName(workOrderProcessVOList.get(i + 1).getUserName());
                    workOrderProcessVOList.get(i).setNextAliasName(workOrderProcessVOList.get(i + 1).getAliasName());
                }

            } else {

                if (workOrder.getStatus().equals(WorkOrderStatus.OK.name())) {
                    workOrderProcessVOList.get(i).setStatus(ProcessHistoryStatusVO.END.name());
                }
            }
        }
        workOrderProcessVOList.get(0).setStatus(ProcessHistoryStatusVO.CREATE.name());

        //查询是否包含表单
        List<Long> existIds = formService.queryFormExist(workOrderProcessVOList.stream().map(WorkOrderProcessHistoryVO::getId).collect(Collectors.toList()), SystemIdentification.WORK_ORDER_SYSTEM);
        workOrderProcessVOList.forEach(e -> {
            if (existIds.contains(e.getId())) {
                e.setIsExistForm(1);
            }
        });
        return workOrderProcessVOList;
    }

    /**
     * 查询工单列表
     *
     * @param pageReq 工单
     * @return 工单
     */
    @Override
    public TableDataInfo<WorkOrderVO> queryPageList(WorkOrderPageReq pageReq) {
        Page<WorkOrderVO> page = pageReq.build();
        List<WorkOrder> list = new ArrayList<>();
        LoginUser loginUser = SecurityUtils.getLoginUser();
        WorkOrder s = new WorkOrder();
        Long userId = loginUser.getUserId();

        //运维系统可以查看所有工单
        if (StringUtils.isNotBlank(pageReq.getSource()) && pageReq.getSource().equals(PlatformEnum.OPERATIONAL.getSiteCode())) {
            userId = null;
        }
        switch (pageReq.getStatus()) {
            case ME:
                //我创建的
                s.setCreateId(loginUser.getUserId());
                list = workOrderMapper.selectWorkOrderList(page, s, pageReq);
                break;
            case NOW:
                //待处理-当前工单流程处理人是我
                s.setHandleUserId(loginUser.getUserId());
                // 工单状态-未完成
                s.setStatus(WorkOrderStatus.NOK.name());
                list = workOrderMapper.selectWorkOrderList(page, s, pageReq);
                break;
            case NO_START:
                // 未开始-当前工单流程处理人是我
                s.setHandleUserId(loginUser.getUserId());
                // 工单状态-未开始
                s.setStatus(WorkOrderStatus.NO_START.name());
                list = workOrderMapper.selectWorkOrderList(page, s, pageReq);
                break;

            case ALL:
                //全部
                //调度员可以查看所有工单
                if (SecurityUtils.whetherDispatcherUser()) {
                    userId = null;
                }
            case NOK:
                //未完成 = 我创建的 or 我处理过的 and 状态是未完成
            case OK:
                //完成 = 我创建的 or 我处理过的 and 状态是完成
            case EVALUATE:
                //已评价 = 我创建的 or 我处理过的 and 状态是完成 and 已评价
            case NO_EVALUATE:
                //待评价 = 我创建的 or 我处理过的 and 状态是完成 and 未评价
                list = workOrderMapper.selectWorkOrderListByStatus(page, pageReq.getStatus().name(), userId, pageReq);
                break;
            default:
                break;
        }

        page.setRecords(WorkOrderConvert.INSTANCE.convertList(list));
        return TableDataInfo.build(page);
    }

    /**
     * 新增工单
     *
     * @param createReq 工单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public WorkOrderVO createWorkOrder(WorkOrderCreateReq createReq) {

        // 复制模板流程添加到工单流程
        List<TemplateProcess> templateProcessList = itemplateService.queryTemplateProcessList(createReq.getTemplateId());
        if (CollectionUtils.isEmpty(templateProcessList)) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }

        List<WorkOrderProcess> workOrderProcessesList = WorkOrderProcessConvert.INSTANCE.templateProcessConvertList(templateProcessList);
        workOrderProcessesList.get(0).setStatus(ProcessStatus.AGREE.name());

        //查询具体要通知的人
        queryUserIdByProcessType(workOrderProcessesList.get(1));
        workOrderProcessesList.get(1).setStatus(ProcessStatus.NOW.name());

        // 添加工单主数据
        WorkOrder workOrder = WorkOrderConvert.INSTANCE.convert(createReq);

        //根据渠道赋值发起人信息到流程开始和结束节点
        setCreateUserToProcess(createReq.getSource(), workOrderProcessesList, workOrder);

        workOrder.setStatus(this.getWorkOrderStatus(createReq.getStartTime()).name());

        if(null == createReq.getStartTime()) {
            workOrder.setStartTime(DateUtils.getNowDate());
        }

        //工单创建完成默认自己确认 流转到第二个人(人 组 系统)
        workOrder.setHandleUserId(workOrderProcessesList.get(1).getUserId());
        workOrderMapper.insert(workOrder);


        workOrderProcessesList.forEach(e -> e.setWorkOrderId(workOrder.getId()));
        workOrderProcessMapper.insertBatch(workOrderProcessesList);


        //创建表单
        List<FormCreateReq> createReqList = new ArrayList<>();
        for (int i = 0; i < templateProcessList.size(); i++) {
            FormCreateReq formCreateReq = new FormCreateReq();
            formCreateReq.setFormTemplateContactId(templateProcessList.get(i).getId());
            formCreateReq.setContactId(workOrderProcessesList.get(i).getId());
            formCreateReq.setSystem(SystemIdentification.WORK_ORDER_SYSTEM);
            createReqList.add(formCreateReq);
        }
        formService.createForm(createReqList);


        //通知第二个人(人 组 系统)
        workOrderNoticeService.noticeByProcess(workOrderProcessesList.get(1));

        WorkOrderVO workOrderVO = WorkOrderConvert.INSTANCE.convert(workOrder);

        // 创建工单
        workOrderProducer.sendCreateWorkOrder(workOrderVO);
        return workOrderVO;
    }

    /**
     * 更新工单
     *
     * @param updateReq 工单
     * @return 结果
     */
    @Override
    public Boolean updateWorkOrder(WorkOrderUpdateReq updateReq) {
        WorkOrder workOrder = WorkOrderConvert.INSTANCE.convert(updateReq);
        return workOrderMapper.updateById(workOrder) > 0;
    }

    /**
     * 根据工单编号查询工单流程
     *
     * @param workOrderId
     * @return
     */
    @Override
    public List<WorkOrderProcess> queryWorkOrderProcessList(Long workOrderId) {

        List<WorkOrderProcess> workOrderProcessList = workOrderProcessMapper.selectWorkOrderProcessListByWorkOrderId(workOrderId);

        if (CollectionUtils.isEmpty(workOrderProcessList)) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }

        return workOrderProcessList;
    }

    /**
     * 处理工单
     *
     * @param handleReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean handle(WorkOrderHandleReq handleReq) {

        LoginUser loginUser = SecurityUtils.getLoginUser();

        WorkOrder workOrder = workOrderMapper.selectById(handleReq.getWorkOrderId());
        if (ObjectUtils.isEmpty(workOrder) || workOrder.getStatus().equalsIgnoreCase(WorkOrderStatus.OK.name())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }

        // 判断是否是处理人或调度员
        if (!loginUser.getUserId().equals(workOrder.getHandleUserId()) && !SecurityUtils.whetherDispatcherUser()) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.FORBIDDEN);
        }

        // 工单未开始
        if (WorkOrderStatus.NO_START.name().equals(workOrder.getStatus())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.WORK_ORDER_NOT_START);
        }

        List<WorkOrderProcess> list = queryWorkOrderProcessList(handleReq.getWorkOrderId());
        WorkOrderProcess now = list.stream().filter(e -> e.getStatus().equalsIgnoreCase(ProcessStatus.NOW.name())).findFirst().get();
        now.setEvaluate(handleReq.getEvaluate());
        now.setEvaluatePicUrl(handleReq.getEvaluatePicUrl());
        now.setStatus(handleReq.getStatus().name());
        if (SecurityUtils.whetherDispatcherUser() && !loginUser.getUserId().equals(workOrder.getHandleUserId())) {
            now.setIsDispatcherUser(true);
        } else {
            now.setIsDispatcherUser(false);
        }
        workOrderProcessMapper.updateWorkOrderProcess(now);

        //填写表单内容保存
        formService.addFormContent(handleReq.getFormElementContentReqs());

        switch (handleReq.getStatus()) {
            case AGREE:
                //同意
                handleByAgree(list, workOrder, now);
                break;
            case REJECT:
                //驳回
                handleByReject(list, workOrder, now);
                break;
            case ADD:
                //指定人员
                handleByAdd(workOrder, now, handleReq);
                break;
            default:
                break;
        }

        return true;
    }

    /**
     * 根据流程查询具体要通知的人
     * 流程类型 人 组 系统
     * 如果是人直接返回
     * 如果是组调用打卡系统或用户系统确认到人
     * 如果打卡系统为空查询该组的负责人
     * 负责人为空随便给组内一个人
     *
     * @param workOrderProcess
     * @return
     */
    public Long queryUserIdByProcessType(WorkOrderProcess workOrderProcess) {
        if (ObjectUtils.isEmpty(workOrderProcess)) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }

        //如果是组 需要确认到人
        if (workOrderProcess.getProcessType().equalsIgnoreCase(ProcessType.GROUP.name())) {
            //查询负责人
            SysDept sysDept = sysDeptMapper.selectById(workOrderProcess.getGroupId());
            if (null == sysDept) {
                //负责人为空 给其他组员
                List<SysUser> sysUserList = userService.queryUserListByDeptId(workOrderProcess.getGroupId());
                if (CollectionUtils.isEmpty(sysUserList)) {
                    throw ServiceExceptionUtil.exception(ErrorCodeConstants.GROUP_USER_NOT_EXISTS);
                }
                workOrderProcess.setUserName(sysUserList.get(0).getNickName());
                workOrderProcess.setUserId(sysUserList.get(0).getUserId());

            } else {
                workOrderProcess.setUserName(sysDept.getLeader());
                workOrderProcess.setUserId(0L);
            }

        }

        return workOrderProcess.getUserId();
    }

    /**
     * 同意工单
     *
     * @param list
     * @param workOrder
     * @param now
     */
    @Transactional(rollbackFor = Exception.class)
    void handleByAgree(List<WorkOrderProcess> list, WorkOrder workOrder, WorkOrderProcess now) {

        //更新工单数据
        workOrder.setIsReject(0);


        //判断是否最后一个流程
        if (now.getIsLast().equals(0)) {

            WorkOrderProcess next = list.get(now.getProcessIndex());

            //更新通知处理人
            workOrder.setHandleUserId(queryUserIdByProcessType(next));


            //更新下一个流程状态为处理中
            next.setStatus(ProcessStatus.NOW.name());
            workOrderProcessMapper.updateWorkOrderProcess(next);

            //通知下一个人
            workOrderNoticeService.noticeByProcess(next);

            workOrderMapper.updateWorkOrder(workOrder);

            //如果是运维系统工单到倒数第二个流程就自动结束
            if (StringUtils.isNotBlank(workOrder.getSource()) && workOrder.getSource().equals(PlatformEnum.OPERATIONAL.getSiteCode()) &&
                    next.getIsLast().equals(1)) {
                autoOkWorkOrder(workOrder.getId());
            }

        } else {

            workOrder.setHandleUserId(null);
            workOrder.setStatus(WorkOrderStatus.OK.name());
            workOrder.setCompleteTime(new Date());
            workOrderMapper.updateWorkOrder(workOrder);

        }


    }


    /**
     * 驳回工单
     * 1. 判断上个节点是否是退回节点 如果是 寻找上一个自己的前一个节点继续判断 以此类推 直到找到非退回节点
     * 2. 如果不是新增节点则还需要加自己
     *
     * @param list
     * @param workOrder
     * @param now
     */
    @Transactional(rollbackFor = Exception.class)
    void handleByReject(List<WorkOrderProcess> list, WorkOrder workOrder, WorkOrderProcess now) {
        workOrder.setIsReject(1);
        int nowIndex = now.getProcessIndex();

        //当前如果是第二个人驳回 工单直接结束
        if (list.get(1).getUserId().equals(now.getUserId()) && now.getIsNew().equals(0)) {

            //删除多余流程
            workOrderProcessMapper.deleteWorkOrderProcessByProcessIndex(workOrder.getId(), nowIndex);

            //添加结束流程
            WorkOrderProcess workOrderProcessEnd = WorkOrderProcessConvert.INSTANCE.templateProcessConvert(list.get(0));
            workOrderProcessEnd.setProcessIndex(nowIndex + 1);
            workOrderProcessEnd.setIsLast(1);
            workOrderProcessMapper.insert(workOrderProcessEnd);

            workOrder.setHandleUserId(null);
            workOrder.setStatus(WorkOrderStatus.OK.name());
            workOrder.setCompleteTime(new Date());
            workOrderMapper.updateWorkOrder(workOrder);

            //通知创建人
            workOrderNoticeService.noticeByProcess(list.get(0));

            //工单完成推送消息到运维系统
            pushDataOperationalByWorkOrder(workOrder);

            return;

        }


        WorkOrderProcess next;
        List<WorkOrderProcess> workOrderProcessList = new ArrayList<>();

        //1. 判断上个节点是否是退回节点 如果是 寻找上一个自己的前一个节点继续判断 以此类推 直到找到非退回节点
        WorkOrderProcess preProcess = getPreNoAddUserProcess(list, nowIndex - 2, now.getUserId());
        next = WorkOrderProcessConvert.INSTANCE.convertThis(preProcess);
        next.setStatus(ProcessStatus.NOW.name());
        next.setProcessIndex(nowIndex + 1);

        //判单加的节点跟下一个节点是否重复 重复则不加 但需要更新状态为NOW
        if (!list.get(nowIndex).getUserId().equals(next.getUserId())) {
            workOrderProcessList.add(next);
        } else {
            WorkOrderProcess oldNext = list.get(nowIndex);
            oldNext.setStatus(ProcessStatus.NOW.name());
            workOrderProcessMapper.updateWorkOrderProcess(oldNext);
        }

        //2. 如果不是新增节点则还需要加自己
        if (!now.getIsNew().equals(1)) {

            WorkOrderProcess next2 = WorkOrderProcessConvert.INSTANCE.convertThis(now);
            next2.setProcessIndex(nowIndex + 2);
            workOrderProcessList.add(next2);

        }
        //更新通知处理人
        workOrder.setHandleUserId(queryUserIdByProcessType(next));

        //要先更新索引再 保存新增流程 是否index会出现重复
        if (CollectionUtils.isNotEmpty(workOrderProcessList)) {
            workOrderProcessMapper.updataProcessIndex(workOrder.getId(), nowIndex, workOrderProcessList.size());
            workOrderProcessMapper.insertBatch(workOrderProcessList);
        }


        workOrderMapper.updateWorkOrder(workOrder);

        //通知(人 组 系统)
        workOrderNoticeService.noticeByProcess(next);
    }


    /**
     * 指定人员
     *
     * @param workOrder
     * @param now
     * @param handleReq
     */
    @Transactional(rollbackFor = Exception.class)
    void handleByAdd(WorkOrder workOrder, WorkOrderProcess now, WorkOrderHandleReq handleReq) {
        workOrder.setIsReject(0);

        List<WorkOrderProcess> addProcessList = new ArrayList<>();
        WorkOrderProcess next = WorkOrderProcessConvert.INSTANCE.convertAdd(handleReq);
        next.setStatus(ProcessStatus.NOW.name());
        next.setProcessIndex(now.getProcessIndex() + 1);
        next.setIsNew(1);
        addProcessList.add(next);

        WorkOrderProcess next2 = WorkOrderProcessConvert.INSTANCE.convertThis(now);
        next2.setProcessIndex(now.getProcessIndex() + 2);
        addProcessList.add(next2);

        //更新通知处理人
        workOrder.setHandleUserId(queryUserIdByProcessType(next));

        //先更新索引 再保存新指定人
        //保存新指定人后还需要添加自己
        workOrderProcessMapper.updataProcessIndex(workOrder.getId(), now.getProcessIndex(), addProcessList.size());

        workOrderProcessMapper.insertBatch(addProcessList);

        //给下个人添加表单信息
        AddUserFormCreateReq addUserFormCreateReq = new AddUserFormCreateReq();
        if (null != handleReq.getNextFormId()) {
            addUserFormCreateReq.setFormWarehouseId(handleReq.getNextFormId());
            addUserFormCreateReq.setContactId(next.getId());
            addUserFormCreateReq.setSystem(SystemIdentification.WORK_ORDER_SYSTEM);
            formService.addUserCreateForm(addUserFormCreateReq);
        }

        workOrderMapper.updateWorkOrder(workOrder);

        //通知(人 组 系统)
        workOrderNoticeService.noticeByProcess(next);
    }


    /**
     * 工单评价
     *
     * @param evaluateReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean evaluate(WorkOrderEvaluateReq evaluateReq) {

        validateWorkOrderExists(evaluateReq.getWorkOrderId());

        WorkOrder workOrder = workOrderMapper.selectById(evaluateReq.getWorkOrderId());
        if (!workOrder.getStatus().equalsIgnoreCase(WorkOrderStatus.OK.name())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }

        workOrder.setEvaluate(evaluateReq.getEvaluate());
        workOrder.setScore(evaluateReq.getScore());
        workOrderMapper.updateWorkOrder(workOrder);

        // 工单评价
        workOrderProducer.sendEvaluateWorkOrder(evaluateReq);
        return true;
    }


    /**
     * Dashbord统计
     *
     * @return
     */
    @Override
    public WorkOrderEvaluateVO dashBoradSummary() {
        WorkOrderPageReq pageQuery = new WorkOrderPageReq();
        pageQuery.setPageNum(1);
        pageQuery.setPageSize(1);

        pageQuery.setStatus(ProcessPageStatusReq.ALL);
        long allWorkOrderTotal = queryPageList(pageQuery).getTotal();

        pageQuery.setStatus(ProcessPageStatusReq.OK);
        long okWorkOrderTotal = queryPageList(pageQuery).getTotal();

        pageQuery.setStatus(ProcessPageStatusReq.NOK);
        long nOkWorkOrderTotal = queryPageList(pageQuery).getTotal();

        pageQuery.setStatus(ProcessPageStatusReq.NOW);
        long nowWorkOrderTotal = queryPageList(pageQuery).getTotal();

        pageQuery.setStatus(ProcessPageStatusReq.ME);
        long meWorkOrderTotal = queryPageList(pageQuery).getTotal();

        long templateTotal = itemplateService.queryTotal();

        WorkOrderEvaluateVO workOrderEvaluateVO = new WorkOrderEvaluateVO();
        workOrderEvaluateVO.setAllWorkOrderTotal(allWorkOrderTotal);
        workOrderEvaluateVO.setOkWorkOrderTotal(okWorkOrderTotal);
        workOrderEvaluateVO.setNOkWorkOrderTotal(nOkWorkOrderTotal);
        workOrderEvaluateVO.setNowWorkOrderTotal(nowWorkOrderTotal);
        workOrderEvaluateVO.setMeWorkOrderTotal(meWorkOrderTotal);
        workOrderEvaluateVO.setTemplateTotal(templateTotal);

        return workOrderEvaluateVO;
    }


    /**
     * 工单最终确认
     *
     * @param workOrderId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean autoOkWorkOrder(Long workOrderId) {
        WorkOrder workOrder = workOrderMapper.selectById(workOrderId);
        if (null == workOrder) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }

        List<WorkOrderProcess> workOrderProcessList = workOrderProcessMapper.selectWorkOrderProcessListByWorkOrderId(workOrder.getId());
        WorkOrderProcess lastProcess = workOrderProcessList.get(workOrderProcessList.size() - 1);

        // 校验是否到最后确认环境
        if (!lastProcess.getStatus().equals(ProcessStatus.NOW.name())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }

        lastProcess.setStatus(ProcessStatus.AGREE.name());
        lastProcess.setEvaluate("自动确认");
        lastProcess.setIsDispatcherUser(false);
        workOrderProcessMapper.updateWorkOrderProcess(lastProcess);

        workOrder.setIsReject(0);
        workOrder.setHandleUserId(null);
        workOrder.setStatus(WorkOrderStatus.OK.name());
        workOrder.setCompleteTime(new Date());
        workOrderMapper.updateWorkOrder(workOrder);

        //工单完成推送消息到运维系统
        pushDataOperationalByWorkOrder(workOrder);

        return true;
    }

    /**
     * 开启工单
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean start(Long id) {
        validateWorkOrderExists(id);
        WorkOrder workOrder = workOrderMapper.selectById(id);

        if(!workOrder.getStatus().equals(WorkOrderStatus.NO_START.name())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.WORK_ORDER_ALREADY_STARTED);
        }

        workOrder.setStatus(WorkOrderStatus.NOK.name());
        workOrder.setStartTime(new Date());
        workOrder.setUpdateTime(DateUtils.getNowDate());

        return workOrderMapper.updateById(workOrder) > 0;
    }

    /**
     * 发送工单消息
     *
     * @param workOrder 工单信息
     * @param templateEnum 消息类型
     * @param templateParam 参数
     * @return 结果
     */
    @Override
    public void sendWorkOrderMsg(WorkOrder workOrder, MessageTemplateEnum templateEnum, Map<String,String> templateParam) {
        BizMessageInfoCreateDTO bizMessageInfoCreateDTO = new BizMessageInfoCreateDTO();
        bizMessageInfoCreateDTO.setMessageTemplate(templateEnum);
        bizMessageInfoCreateDTO.setBusinessId(workOrder.getId());
        // 给申请人发送待审核通知
        bizMessageInfoCreateDTO.setUserIds(userService.getAdminUserIds());
        bizMessageInfoCreateDTO.setMessageSender(MessageTemplateEnum.getMessageByValue(templateEnum.getDictValue()));
        bizMessageInfoCreateDTO.setTemplateParam(templateParam);

        messageBizServiceApi.createMessage(bizMessageInfoCreateDTO);
    }

    /**
     * 发送工单消息
     *
     * @param workOrderId 工单ID
     * @param userIds 用户ID
     * @param templateEnum 消息类型
     * @param templateParam 参数
     */
    @Override
    public void sendWorkOrderMsg(Long workOrderId, List<Long> userIds, MessageTemplateEnum templateEnum, Map<String, String> templateParam) {
        BizMessageInfoCreateDTO bizMessageInfoCreateDTO = new BizMessageInfoCreateDTO();
        bizMessageInfoCreateDTO.setMessageTemplate(templateEnum);
        bizMessageInfoCreateDTO.setBusinessId(workOrderId);
        bizMessageInfoCreateDTO.setUserIds(userIds);
        bizMessageInfoCreateDTO.setMessageSender(MessageTemplateEnum.getMessageByValue(templateEnum.getDictValue()));
        bizMessageInfoCreateDTO.setTemplateParam(templateParam);

        messageBizServiceApi.createMessage(bizMessageInfoCreateDTO);
    }

    /**
     * 判断上个节点是否是退回节点
     * 如果是 寻找上一个自己的前一个节点继续判断 以此类推 直到找到非退回节点
     * 如果不是则返回
     *
     * @param list
     * @param index
     * @param userId
     * @return
     */
    private WorkOrderProcess getPreNoAddUserProcess(List<WorkOrderProcess> list, int index, Long userId) {
        WorkOrderProcess process = list.get(index);
        if (process.getStatus().equals(ProcessStatus.REJECT.name())) {
            //当前为撤回节点上个节点 截取到当前节点
            List<WorkOrderProcess> tmpList = list.subList(0, process.getProcessIndex()).stream().filter(e -> e.getUserId().equals(userId)).collect(Collectors.toList());
            int preIndex = tmpList.get(tmpList.size() - 1).getProcessIndex();
            return getPreNoAddUserProcess(list, preIndex - 2, userId);
        }
        return process;
    }

    /**
     * 根据渠道赋值发起人信息到流程开始和结束节点
     *
     * @param list
     * @param source
     */
    private void setCreateUserToProcess(String source, List<WorkOrderProcess> list, WorkOrder workOrder) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String nickName = loginUser.getUsername();

        list.get(0).setUserId(userId);
        list.get(0).setUserName(nickName);
        list.get(list.size() - 1).setUserId(userId);
        list.get(list.size() - 1).setUserName(nickName);

        workOrder.setCreateId(userId);
        workOrder.setCreateName(nickName);
    }

    /**
     * 工单完成推送消息到运维系统
     *
     * @param workOrder 工单
     */
    @Transactional(rollbackFor = Exception.class)
    void pushDataOperationalByWorkOrder(WorkOrder workOrder) {


        final BigDecimal[] offerSum = {new BigDecimal(0)};

        //查询并计算报价
        List<WorkOrderProcess> workOrderProcessList = workOrderProcessMapper.selectWorkOrderProcessListByWorkOrderId(workOrder.getId());
        List<Long> processIds = workOrderProcessList.stream().map(WorkOrderProcess::getId).collect(Collectors.toList());
        List<FormElementVO> list = formService.queryStatisticsForm(processIds, FormBizName.ItemUnitPriceQuantity, SystemIdentification.WORK_ORDER_SYSTEM);

        if (CollectionUtils.isNotEmpty(list)) {
            // list 中包含一个工单中多个流程中的报价单 每个流程可能包含多份填写报价单 需要取最新流程中的多份报价单数量相加
            // 根据key倒叙取第一组数据
            TreeMap<Long, List<FormElementVO>> collect = list.stream().collect(Collectors.groupingBy(FormElementVO::getFormId, TreeMap::new, Collectors.toList()));
            List<FormElementVO> formElementVOList = collect.descendingMap().firstEntry().getValue();
            // 继续按照bizKey分组
            Map<String, List<FormElementVO>> bizKeyCollect = formElementVOList.stream().collect(Collectors.groupingBy(FormElementVO::getBizKey, Collectors.toList()));
            bizKeyCollect.forEach((k, v) -> {
                // v继续按照bizField分组 分组完list只会有一个
                Map<String, List<FormElementVO>> bizFieldCollect = v.stream().collect(Collectors.groupingBy(FormElementVO::getBizField, Collectors.toList()));
                BigDecimal unitPrice = new BigDecimal(bizFieldCollect.get(FormBizField.unitPrice.name()).get(0).getValueString());
                BigDecimal quantity = new BigDecimal(bizFieldCollect.get(FormBizField.quantity.name()).get(0).getValueString());
                BigDecimal offer = unitPrice.multiply(quantity);
                offerSum[0] = offerSum[0].add(offer);
            });
        }

        //区分计算0和null
        String offer = CollectionUtils.isEmpty(list) ? null : offerSum[0].toPlainString();
        workOrder.setOffer(offer);
        workOrderMapper.updateWorkOrder(workOrder);

        // 推送运维系统
        if (StringUtils.isNotBlank(workOrder.getSource()) && workOrder.getSource().equals(PlatformEnum.OPERATIONAL.getSiteCode())) {

        }
    }

    /**
     * 获取工单状态
     * 无开始时间和历史时间立即创建工单
     * 未来时间：工单未开始
     *
     * @param startTime 开始时间
     * @return
     */
    private WorkOrderStatus getWorkOrderStatus(Date startTime) {
        boolean flag = null != startTime && startTime.after(DateUtils.getNowDate());
        return flag ? WorkOrderStatus.NO_START : WorkOrderStatus.NOK;
    }

}
