package com.flyhigh.sms.service;

import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.sms.domain.vo.messageUserDetail.*;

import java.util.Collection;
import java.util.List;

/**
 * 消息接收人Service接口
 *
 * @author flyhigh
 * @date 2022-03-13
 */
public interface IBizMessageUserDetailService {

    /**
     * 查询消息接收人
     *
     * @param id 消息接收人主键
     * @return 消息接收人
     */
    BizMessageUserDetailVO queryById(Long id);

    /**
     * 查询消息接收人列表
     *
     * @param pageVO 消息接收人
     * @return 消息接收人集合
     */
    TableDataInfo<BizMessageUserDetailVO> queryPageList(BizMessageUserDetailPageReq pageVO);

    /**
     * 查询消息接收人列表
     *
     * @param exportReq 消息接收人
     * @return 消息接收人集合
     */
    List<BizMessageUserDetailExcelVO> queryList(BizMessageUserDetailExportReq exportReq);

    /**
     * 创建消息接收人
     *
     * @param createReq 消息接收人
     * @return 结果
     */
    Boolean createBizMessageUserDetail(BizMessageUserDetailCreateReq createReq);

    /**
     * 修改消息接收人
     *
     * @param updateReq 消息接收人
     * @return 结果
     */
    Boolean updateBizMessageUserDetail(BizMessageUserDetailUpdateReq updateReq);

    /**
     * 校验并批量删除消息接收人信息
     *
     * @param ids     需要删除的消息接收人主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 消息全部已读
     *
     * @return 结果
     */
    Boolean allRead();

}
