package com.flyhigh.sms.service;

import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.sms.domain.vo.pushUserAlias.*;

import java.util.Collection;
import java.util.List;

/**
 * 用户个推别名Service接口
 *
 * @author flyhigh
 * @date 2022-03-31
 */
public interface IBizPushUserAliasService {

    /**
     * 查询用户个推别名
     *
     * @param id 用户个推别名主键
     * @return 用户个推别名
     */
    BizPushUserAliasVO queryById(Long id);

    /**
     * 查询用户个推别名列表
     *
     * @param pageVO 用户个推别名
     * @return 用户个推别名集合
     */
    TableDataInfo<BizPushUserAliasVO> queryPageList(BizPushUserAliasPageReq pageVO);

    /**
     * 查询用户个推别名列表
     *
     * @param exportReq 用户个推别名
     * @return 用户个推别名集合
     */
    List<BizPushUserAliasExcelVO> queryList(BizPushUserAliasExportReq exportReq);

    /**
     * 创建用户个推别名
     *
     * @param createReq 用户个推别名
     * @return 结果
     */
    Boolean createBizPushUserAlias(BizPushUserAliasCreateReq createReq);

    /**
     * 修改用户个推别名
     *
     * @param updateReq 用户个推别名
     * @return 结果
     */
    Boolean updateBizPushUserAlias(BizPushUserAliasUpdateReq updateReq);

    /**
     * 校验并批量删除用户个推别名信息
     *
     * @param ids     需要删除的用户个推别名主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 获得用户个推别名列表
     *
     * @param ids 编号
     */
    List<BizPushUserAliasVO> getBizPushUserAliasList(Collection<Long> ids);

}
