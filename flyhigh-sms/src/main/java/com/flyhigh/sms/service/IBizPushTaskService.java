package com.flyhigh.sms.service;

import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.sms.domain.vo.pushTask.*;

import java.util.Collection;
import java.util.List;

/**
 * 推送任务Service接口
 *
 * @author flyhigh
 * @date 2022-03-31
 */
public interface IBizPushTaskService {

    /**
     * 查询推送任务
     *
     * @param id 推送任务主键
     * @return 推送任务
     */
    BizPushTaskVO queryById(Long id);

    /**
     * 查询推送任务列表
     *
     * @param pageVO 推送任务
     * @return 推送任务集合
     */
    TableDataInfo<BizPushTaskVO> queryPageList(BizPushTaskPageReq pageVO);

    /**
     * 查询推送任务列表
     *
     * @param exportReq 推送任务
     * @return 推送任务集合
     */
    List<BizPushTaskExcelVO> queryList(BizPushTaskExportReq exportReq);

    /**
     * 创建推送任务
     *
     * @param createReq 推送任务
     * @return 结果
     */
    Boolean createBizPushTask(BizPushTaskCreateReq createReq);

    /**
     * 修改推送任务
     *
     * @param updateReq 推送任务
     * @return 结果
     */
    Boolean updateBizPushTask(BizPushTaskUpdateReq updateReq);

    /**
     * 校验并批量删除推送任务信息
     *
     * @param ids     需要删除的推送任务主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 获得推送任务列表
     *
     * @param ids 编号
     */
    List<BizPushTaskVO> getBizPushTaskList(Collection<Long> ids);

}
