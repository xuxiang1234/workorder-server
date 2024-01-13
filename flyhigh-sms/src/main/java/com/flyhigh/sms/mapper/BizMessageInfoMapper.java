package com.flyhigh.sms.mapper;

import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.core.query.LambdaQueryWrapperPlus;
import com.flyhigh.sms.domain.BizMessageInfo;
import com.flyhigh.sms.domain.vo.messageInfo.BizMessageInfoExportReq;
import com.flyhigh.sms.domain.vo.messageInfo.BizMessageInfoPageReq;

import java.util.List;


/**
 * 消息Mapper接口
 *
 * @author flyhigh
 * @date 2022-03-13
 */
public interface BizMessageInfoMapper extends BaseMapperPlus<BizMessageInfoMapper, BizMessageInfo> {

    /**
     * 查询消息
     *
     * @param id 消息主键
     * @return 消息
     */
    BizMessageInfo selectBizMessageInfoById(Long id);

    /**
     * 查询消息列表
     *
     * @param bizMessageInfo 消息
     * @return 消息集合
     */
    List<BizMessageInfo> selectBizMessageInfoList(BizMessageInfo bizMessageInfo);

    /**
     * 新增消息
     *
     * @param bizMessageInfo 消息
     * @return 结果
     */
    int insertBizMessageInfo(BizMessageInfo bizMessageInfo);

    /**
     * 修改消息
     *
     * @param bizMessageInfo 消息
     * @return 结果
     */
    int updateBizMessageInfo(BizMessageInfo bizMessageInfo);

    /**
     * 删除消息
     *
     * @param id 消息主键
     * @return 结果
     */
    int deleteBizMessageInfoById(Long id);

    /**
     * 批量删除消息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteBizMessageInfoByIds(Long[] ids);

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    default TableDataInfo<BizMessageInfo> selectPage(BizMessageInfoPageReq req) {
        return selectPage(req, new LambdaQueryWrapperPlus<BizMessageInfo>()
                .eqIfPresent(BizMessageInfo::getModuleType, req.getModuleType())
                .eqIfPresent(BizMessageInfo::getBusinessId, req.getBusinessId())
                .eqIfPresent(BizMessageInfo::getMessageType, req.getMessageType())
                .eqIfPresent(BizMessageInfo::getComponent, req.getComponent())
                .eqIfPresent(BizMessageInfo::getCustomParameters, req.getCustomParameters())
                .eqIfPresent(BizMessageInfo::getContent, req.getContent())
                .eqIfPresent(BizMessageInfo::getMessageSender, req.getMessageSender())
                .orderByDesc(BizMessageInfo::getId));
    }

    /**
     * 导出列表查询
     *
     * @param req
     * @return
     */
    default List<BizMessageInfo> selectList(BizMessageInfoExportReq req) {
        return selectList(new LambdaQueryWrapperPlus<BizMessageInfo>()
                .eqIfPresent(BizMessageInfo::getModuleType, req.getModuleType())
                .eqIfPresent(BizMessageInfo::getBusinessId, req.getBusinessId())
                .eqIfPresent(BizMessageInfo::getMessageType, req.getMessageType())
                .eqIfPresent(BizMessageInfo::getComponent, req.getComponent())
                .eqIfPresent(BizMessageInfo::getCustomParameters, req.getCustomParameters())
                .eqIfPresent(BizMessageInfo::getContent, req.getContent())
                .eqIfPresent(BizMessageInfo::getMessageSender, req.getMessageSender())
                .orderByDesc(BizMessageInfo::getId));
    }

}
