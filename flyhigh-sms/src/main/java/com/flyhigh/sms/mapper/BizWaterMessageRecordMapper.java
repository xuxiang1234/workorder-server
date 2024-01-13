package com.flyhigh.sms.mapper;

import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.common.core.query.LambdaQueryWrapperPlus;
import com.flyhigh.sms.domain.BizWaterMessageRecord;
import com.flyhigh.sms.domain.vo.waterMessageRecord.BizWaterMessageRecordExportReq;

import java.util.List;


/**
 * 业务消息流水Mapper接口
 *
 * @author flyhigh
 * @date 2022-04-21
 */
public interface BizWaterMessageRecordMapper extends BaseMapperPlus<BizWaterMessageRecordMapper, BizWaterMessageRecord> {

    /**
     * 查询业务消息流水
     *
     * @param id 业务消息流水主键
     * @return 业务消息流水
     */
    BizWaterMessageRecord selectBizWaterMessageRecordById(Long id);

    /**
     * 查询业务消息流水列表
     *
     * @param bizWaterMessageRecord 业务消息流水
     * @return 业务消息流水集合
     */
    List<BizWaterMessageRecord> selectBizWaterMessageRecordList(BizWaterMessageRecord bizWaterMessageRecord);

    /**
     * 新增业务消息流水
     *
     * @param bizWaterMessageRecord 业务消息流水
     * @return 结果
     */
    int insertBizWaterMessageRecord(BizWaterMessageRecord bizWaterMessageRecord);

    /**
     * 修改业务消息流水
     *
     * @param bizWaterMessageRecord 业务消息流水
     * @return 结果
     */
    int updateBizWaterMessageRecord(BizWaterMessageRecord bizWaterMessageRecord);

    /**
     * 删除业务消息流水
     *
     * @param id 业务消息流水主键
     * @return 结果
     */
    int deleteBizWaterMessageRecordById(Long id);

    /**
     * 批量删除业务消息流水
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteBizWaterMessageRecordByIds(Long[] ids);


    /**
     * 导出列表查询
     *
     * @param req
     * @return
     */
    default List<BizWaterMessageRecord> selectList(BizWaterMessageRecordExportReq req) {
        return selectList(new LambdaQueryWrapperPlus<BizWaterMessageRecord>()
                .eqIfPresent(BizWaterMessageRecord::getModuleTypeDesc, req.getModuleTypeDesc())
                .eqIfPresent(BizWaterMessageRecord::getModuleType, req.getModuleType())
                .eqIfPresent(BizWaterMessageRecord::getBusinessId, req.getBusinessId())
                .eqIfPresent(BizWaterMessageRecord::getMessageType, req.getMessageType())
                .eqIfPresent(BizWaterMessageRecord::getMessageDesc, req.getMessageDesc())
                .eqIfPresent(BizWaterMessageRecord::getCustomParameters, req.getCustomParameters())
                .eqIfPresent(BizWaterMessageRecord::getContent, req.getContent())
                .eqIfPresent(BizWaterMessageRecord::getMessageSender, req.getMessageSender())
                .eqIfPresent(BizWaterMessageRecord::getCreateUserId, req.getCreateUserId())
                .eqIfPresent(BizWaterMessageRecord::getDeptId, req.getDeptId())
                .likeIfPresent(BizWaterMessageRecord::getDeptName, req.getDeptName())
                .orderByDesc(BizWaterMessageRecord::getId));
    }

}
