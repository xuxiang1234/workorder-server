package com.flyhigh.sms.service;

import com.flyhigh.api.dto.messageInfo.BizMessageInfoDeleteDTO;
import com.flyhigh.api.dto.messageInfo.BizMessageInfoQueryDTO;
import com.flyhigh.api.dto.messageInfo.BizMessageInfoQueryVO;
import com.flyhigh.api.dto.messageInfo.BizMessageUserDetailDTO;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.MessageTemplateEnum;
import com.flyhigh.sms.domain.BizMessageInfo;
import com.flyhigh.sms.domain.vo.messageInfo.*;

import java.util.Collection;
import java.util.List;

/**
 * 消息Service接口
 *
 * @author flyhigh
 * @date 2022-03-13
 */
public interface IBizMessageInfoService {

    /**
     * 查询消息
     *
     * @param id 消息主键
     * @return 消息
     */
    BizMessageInfoVO queryById(Long id);

    /**
     * 查询消息列表
     *
     * @param pageVO 消息
     * @return 消息集合
     */
    TableDataInfo<BizMessageInfoVO> queryPageList(BizMessageInfoPageReq pageVO);

    /**
     * 查询消息列表
     *
     * @param exportReq 消息
     * @return 消息集合
     */
    List<BizMessageInfoExcelVO> queryList(BizMessageInfoExportReq exportReq);

    /**
     * 创建消息
     *
     * @return 结果
     */
    Boolean createBizMessageInfo(BizMessageInfo bizMessageInfo, List<Long> userIds);

    /**
     * 修改消息
     *
     * @param updateReq 消息
     * @return 结果
     */
    Boolean updateBizMessageInfo(BizMessageInfoUpdateReq updateReq);

    /**
     * 校验并批量删除消息信息
     *
     * @param ids     需要删除的消息主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 获得消息列表
     *
     * @param ids 编号
     */
    List<BizMessageInfoVO> getBizMessageInfoList(Collection<Long> ids);

    /**
     * 删除消息
     *
     * @param bizMessageInfoDeleteDTO
     */
    void deleteMessage(BizMessageInfoDeleteDTO bizMessageInfoDeleteDTO);

    /**
     * 修改消息
     *
     * @param bizMessageInfo
     * @param userIds
     * @param messageTemplate
     * @param whetherCascadeUser
     */
    void editBizMessageInfo(BizMessageInfo bizMessageInfo, List<Long> userIds, MessageTemplateEnum messageTemplate, boolean whetherCascadeUser);

    /**
     * 根据业务id消息接收人
     *
     * @param messageTemplateEnum
     * @param businessId
     * @return
     */
    AjaxResult<List<BizMessageUserDetailDTO>> getMessageRecipients(MessageTemplateEnum messageTemplateEnum, Long businessId);

    /**
     * 删除审批消息
     *
     * @param businessId
     * @param uids
     * @param messageTemplateEnum
     */
    void deleteProcessMsg(Long businessId, List<String> uids, MessageTemplateEnum messageTemplateEnum);

    /**
     * 将审批消息修改成已审批
     *
     * @param businessId
     * @param flowNodeUid
     * @param messageTemplateEnum
     */
    String updateApproveMsg(Long businessId, Long flowNodeUid, MessageTemplateEnum messageTemplateEnum);

}
