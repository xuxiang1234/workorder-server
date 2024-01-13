package com.flyhigh.sms.mapper;

import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.core.query.LambdaQueryWrapperPlus;
import com.flyhigh.sms.domain.BizPushUserCid;
import com.flyhigh.sms.domain.vo.pushUserCid.BizPushUserCidExportReq;
import com.flyhigh.sms.domain.vo.pushUserCid.BizPushUserCidPageReq;

import java.util.List;


/**
 * 用户个推cid关系绑定Mapper接口
 *
 * @author flyhigh
 * @date 2022-03-31
 */
public interface BizPushUserCidMapper extends BaseMapperPlus<BizPushUserCidMapper, BizPushUserCid> {

    /**
     * 查询用户个推cid关系绑定
     *
     * @param id 用户个推cid关系绑定主键
     * @return 用户个推cid关系绑定
     */
    BizPushUserCid selectBizPushUserCidById(Long id);

    /**
     * 查询用户个推cid关系绑定列表
     *
     * @param bizPushUserCid 用户个推cid关系绑定
     * @return 用户个推cid关系绑定集合
     */
    List<BizPushUserCid> selectBizPushUserCidList(BizPushUserCid bizPushUserCid);

    /**
     * 新增用户个推cid关系绑定
     *
     * @param bizPushUserCid 用户个推cid关系绑定
     * @return 结果
     */
    int insertBizPushUserCid(BizPushUserCid bizPushUserCid);

    /**
     * 修改用户个推cid关系绑定
     *
     * @param bizPushUserCid 用户个推cid关系绑定
     * @return 结果
     */
    int updateBizPushUserCid(BizPushUserCid bizPushUserCid);

    /**
     * 删除用户个推cid关系绑定
     *
     * @param id 用户个推cid关系绑定主键
     * @return 结果
     */
    int deleteBizPushUserCidById(Long id);

    /**
     * 批量删除用户个推cid关系绑定
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteBizPushUserCidByIds(Long[] ids);

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    default TableDataInfo<BizPushUserCid> selectPage(BizPushUserCidPageReq req) {
        return selectPage(req, new LambdaQueryWrapperPlus<BizPushUserCid>()
                .eqIfPresent(BizPushUserCid::getUserId, req.getUserId())
                .eqIfPresent(BizPushUserCid::getCid, req.getCid())
                .eqIfPresent(BizPushUserCid::getUserAlias, req.getUserAlias())
                .orderByDesc(BizPushUserCid::getId));
    }

    /**
     * 导出列表查询
     *
     * @param req
     * @return
     */
    default List<BizPushUserCid> selectList(BizPushUserCidExportReq req) {
        return selectList(new LambdaQueryWrapperPlus<BizPushUserCid>()
                .eqIfPresent(BizPushUserCid::getUserId, req.getUserId())
                .eqIfPresent(BizPushUserCid::getCid, req.getCid())
                .eqIfPresent(BizPushUserCid::getUserAlias, req.getUserAlias())
                .orderByDesc(BizPushUserCid::getId));
    }

}
