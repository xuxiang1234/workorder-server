package com.flyhigh.sms.service;

import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.sms.domain.vo.waterMessageRecord.*;

import java.util.Collection;
import java.util.List;

/**
 * 业务消息流水Service接口
 *
 * @author flyhigh
 * @date 2022-04-21
 */
public interface IBizWaterMessageRecordService {

    /**
     * 查询业务消息流水
     *
     * @param id 业务消息流水主键
     * @return 业务消息流水
     */
    BizWaterMessageRecordVO queryById(Long id);

    /**
     * 查询业务消息流水列表
     *
     * @param pageVO 业务消息流水
     * @return 业务消息流水集合
     */
    TableDataInfo<BizWaterMessageRecordVO> queryPageList(BizWaterMessageRecordReq pageVO);

    /**
     * 查询业务消息流水列表
     *
     * @param exportReq 业务消息流水
     * @return 业务消息流水集合
     */
    List<BizWaterMessageRecordExcelVO> queryList(BizWaterMessageRecordExportReq exportReq);

    /**
     * 创建业务消息流水
     *
     * @param createReq 业务消息流水
     * @return 结果
     */
    Boolean createBizWaterMessageRecord(BizWaterMessageRecordCreateReq createReq);

    /**
     * 修改业务消息流水
     *
     * @param updateReq 业务消息流水
     * @return 结果
     */
    Boolean updateBizWaterMessageRecord(BizWaterMessageRecordUpdateReq updateReq);

    /**
     * 校验并批量删除业务消息流水信息
     *
     * @param ids     需要删除的业务消息流水主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 获得业务消息流水列表
     *
     * @param ids 编号
     */
    List<BizWaterMessageRecordVO> getBizWaterMessageRecordList(Collection<Long> ids);

    /**
     * 查询列表
     *
     * @param req
     * @return
     */
    AjaxResult<List<BizWaterMessageRecordVO>> waterList(BizWaterMessageRecordReq req);

}
