package com.flyhigh.sms.service;

import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.sms.domain.vo.pushErrorRecord.*;

import java.util.Collection;
import java.util.List;

/**
 * 推送异常Service接口
 *
 * @author flyhigh
 * @date 2022-03-31
 */
public interface IBizPushErrorRecordService {

    /**
     * 查询推送异常
     *
     * @param id 推送异常主键
     * @return 推送异常
     */
    BizPushErrorRecordVO queryById(Long id);

    /**
     * 查询推送异常列表
     *
     * @param pageVO 推送异常
     * @return 推送异常集合
     */
    TableDataInfo<BizPushErrorRecordVO> queryPageList(BizPushErrorRecordPageReq pageVO);

    /**
     * 查询推送异常列表
     *
     * @param exportReq 推送异常
     * @return 推送异常集合
     */
    List<BizPushErrorRecordExcelVO> queryList(BizPushErrorRecordExportReq exportReq);

    /**
     * 创建推送异常
     *
     * @param createReq 推送异常
     * @return 结果
     */
    Boolean createBizPushErrorRecord(BizPushErrorRecordCreateReq createReq);

    /**
     * 修改推送异常
     *
     * @param updateReq 推送异常
     * @return 结果
     */
    Boolean updateBizPushErrorRecord(BizPushErrorRecordUpdateReq updateReq);

    /**
     * 校验并批量删除推送异常信息
     *
     * @param ids     需要删除的推送异常主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 获得推送异常列表
     *
     * @param ids 编号
     */
    List<BizPushErrorRecordVO> getBizPushErrorRecordList(Collection<Long> ids);

}
