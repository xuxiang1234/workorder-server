package com.flyhigh.sms.mapper;

import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.core.query.LambdaQueryWrapperPlus;
import com.flyhigh.common.utils.SecurityUtils;
import com.flyhigh.sms.domain.BizMessageUserDetail;
import com.flyhigh.sms.domain.vo.messageUserDetail.BizMessageUserDetailExportReq;
import com.flyhigh.sms.domain.vo.messageUserDetail.BizMessageUserDetailPageReq;

import java.util.List;


/**
 * 消息接收人Mapper接口
 *
 * @author flyhigh
 * @date 2022-03-13
 */
public interface BizMessageUserDetailMapper extends BaseMapperPlus<BizMessageUserDetailMapper, BizMessageUserDetail> {

    /**
     * 查询消息接收人
     *
     * @param id 消息接收人主键
     * @return 消息接收人
     */
    BizMessageUserDetail selectBizMessageUserDetailById(Long id);

    /**
     * 查询消息接收人列表
     *
     * @param bizMessageUserDetail 消息接收人
     * @return 消息接收人集合
     */
    List<BizMessageUserDetail> selectBizMessageUserDetailList(BizMessageUserDetail bizMessageUserDetail);

    /**
     * 新增消息接收人
     *
     * @param bizMessageUserDetail 消息接收人
     * @return 结果
     */
    int insertBizMessageUserDetail(BizMessageUserDetail bizMessageUserDetail);

    /**
     * 修改消息接收人
     *
     * @param bizMessageUserDetail 消息接收人
     * @return 结果
     */
    int updateBizMessageUserDetail(BizMessageUserDetail bizMessageUserDetail);

    /**
     * 删除消息接收人
     *
     * @param id 消息接收人主键
     * @return 结果
     */
    int deleteBizMessageUserDetailById(Long id);

    /**
     * 批量删除消息接收人
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteBizMessageUserDetailByIds(Long[] ids);

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    default TableDataInfo<BizMessageUserDetail> selectPage(BizMessageUserDetailPageReq req) {
        return selectPage(req, new LambdaQueryWrapperPlus<BizMessageUserDetail>()
                .eqIfPresent(BizMessageUserDetail::getUserId, SecurityUtils.getUserId())
                .eqIfPresent(BizMessageUserDetail::getReceiveStatus, req.getReceiveStatus())
                .orderByDesc(BizMessageUserDetail::getId));
    }

    /**
     * 导出列表查询
     *
     * @param req
     * @return
     */
    default List<BizMessageUserDetail> selectList(BizMessageUserDetailExportReq req) {
        return selectList(new LambdaQueryWrapperPlus<BizMessageUserDetail>()
                .eqIfPresent(BizMessageUserDetail::getMessageId, req.getMessageId())
                .eqIfPresent(BizMessageUserDetail::getUserId, req.getUserId())
                .eqIfPresent(BizMessageUserDetail::getReceiveStatus, req.getReceiveStatus())
                .eqIfPresent(BizMessageUserDetail::getWhetherApprove, req.getWhetherApprove())
                .orderByDesc(BizMessageUserDetail::getId));
    }

}
