package com.flyhigh.sms.mapper;

import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.core.query.LambdaQueryWrapperPlus;
import com.flyhigh.sms.domain.BizPushErrorRecord;
import com.flyhigh.sms.domain.vo.pushErrorRecord.BizPushErrorRecordExportReq;
import com.flyhigh.sms.domain.vo.pushErrorRecord.BizPushErrorRecordPageReq;

import java.util.List;


/**
 * 推送异常Mapper接口
 *
 * @author flyhigh
 * @date 2022-03-31
 */
public interface BizPushErrorRecordMapper extends BaseMapperPlus<BizPushErrorRecordMapper, BizPushErrorRecord> {

    /**
     * 查询推送异常
     *
     * @param id 推送异常主键
     * @return 推送异常
     */
    BizPushErrorRecord selectBizPushErrorRecordById(Long id);

    /**
     * 查询推送异常列表
     *
     * @param bizPushErrorRecord 推送异常
     * @return 推送异常集合
     */
    List<BizPushErrorRecord> selectBizPushErrorRecordList(BizPushErrorRecord bizPushErrorRecord);

    /**
     * 新增推送异常
     *
     * @param bizPushErrorRecord 推送异常
     * @return 结果
     */
    int insertBizPushErrorRecord(BizPushErrorRecord bizPushErrorRecord);

    /**
     * 修改推送异常
     *
     * @param bizPushErrorRecord 推送异常
     * @return 结果
     */
    int updateBizPushErrorRecord(BizPushErrorRecord bizPushErrorRecord);

    /**
     * 删除推送异常
     *
     * @param id 推送异常主键
     * @return 结果
     */
    int deleteBizPushErrorRecordById(Long id);

    /**
     * 批量删除推送异常
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteBizPushErrorRecordByIds(Long[] ids);

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    default TableDataInfo<BizPushErrorRecord> selectPage(BizPushErrorRecordPageReq req) {
        return selectPage(req, new LambdaQueryWrapperPlus<BizPushErrorRecord>()
                .eqIfPresent(BizPushErrorRecord::getMessageId, req.getMessageId())
                .eqIfPresent(BizPushErrorRecord::getUserNumber, req.getUserNumber())
                .eqIfPresent(BizPushErrorRecord::getTryTimes, req.getTryTimes())
                .orderByDesc(BizPushErrorRecord::getId));
    }

    /**
     * 导出列表查询
     *
     * @param req
     * @return
     */
    default List<BizPushErrorRecord> selectList(BizPushErrorRecordExportReq req) {
        return selectList(new LambdaQueryWrapperPlus<BizPushErrorRecord>()
                .eqIfPresent(BizPushErrorRecord::getMessageId, req.getMessageId())
                .eqIfPresent(BizPushErrorRecord::getUserNumber, req.getUserNumber())
                .eqIfPresent(BizPushErrorRecord::getTryTimes, req.getTryTimes())
                .orderByDesc(BizPushErrorRecord::getId));
    }

}
