package com.flyhigh.sms.mapper;

import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.core.query.LambdaQueryWrapperPlus;
import com.flyhigh.sms.domain.BizPushUserAlias;
import com.flyhigh.sms.domain.vo.pushUserAlias.BizPushUserAliasExportReq;
import com.flyhigh.sms.domain.vo.pushUserAlias.BizPushUserAliasPageReq;

import java.util.List;


/**
 * 用户个推别名Mapper接口
 *
 * @author flyhigh
 * @date 2022-03-31
 */
public interface BizPushUserAliasMapper extends BaseMapperPlus<BizPushUserAliasMapper, BizPushUserAlias> {

    /**
     * 查询用户个推别名
     *
     * @param id 用户个推别名主键
     * @return 用户个推别名
     */
    BizPushUserAlias selectBizPushUserAliasById(Long id);

    /**
     * 查询用户个推别名列表
     *
     * @param bizPushUserAlias 用户个推别名
     * @return 用户个推别名集合
     */
    List<BizPushUserAlias> selectBizPushUserAliasList(BizPushUserAlias bizPushUserAlias);

    /**
     * 新增用户个推别名
     *
     * @param bizPushUserAlias 用户个推别名
     * @return 结果
     */
    int insertBizPushUserAlias(BizPushUserAlias bizPushUserAlias);

    /**
     * 修改用户个推别名
     *
     * @param bizPushUserAlias 用户个推别名
     * @return 结果
     */
    int updateBizPushUserAlias(BizPushUserAlias bizPushUserAlias);

    /**
     * 删除用户个推别名
     *
     * @param id 用户个推别名主键
     * @return 结果
     */
    int deleteBizPushUserAliasById(Long id);

    /**
     * 批量删除用户个推别名
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteBizPushUserAliasByIds(Long[] ids);

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    default TableDataInfo<BizPushUserAlias> selectPage(BizPushUserAliasPageReq req) {
        return selectPage(req, new LambdaQueryWrapperPlus<BizPushUserAlias>()
                .eqIfPresent(BizPushUserAlias::getUserId, req.getUserId())
                .eqIfPresent(BizPushUserAlias::getUserAlias, req.getUserAlias())
                .orderByDesc(BizPushUserAlias::getId));
    }

    /**
     * 导出列表查询
     *
     * @param req
     * @return
     */
    default List<BizPushUserAlias> selectList(BizPushUserAliasExportReq req) {
        return selectList(new LambdaQueryWrapperPlus<BizPushUserAlias>()
                .eqIfPresent(BizPushUserAlias::getUserId, req.getUserId())
                .eqIfPresent(BizPushUserAlias::getUserAlias, req.getUserAlias())
                .orderByDesc(BizPushUserAlias::getId));
    }

}
