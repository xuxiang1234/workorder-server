package com.flyhigh.sms.service;

import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.sms.domain.vo.pushUserCid.*;

import java.util.Collection;
import java.util.List;

/**
 * 用户个推cid关系绑定Service接口
 *
 * @author flyhigh
 * @date 2022-03-31
 */
public interface IBizPushUserCidService {

    /**
     * 查询用户个推cid关系绑定
     *
     * @param id 用户个推cid关系绑定主键
     * @return 用户个推cid关系绑定
     */
    BizPushUserCidVO queryById(Long id);

    /**
     * 查询用户个推cid关系绑定列表
     *
     * @param pageVO 用户个推cid关系绑定
     * @return 用户个推cid关系绑定集合
     */
    TableDataInfo<BizPushUserCidVO> queryPageList(BizPushUserCidPageReq pageVO);

    /**
     * 查询用户个推cid关系绑定列表
     *
     * @param exportReq 用户个推cid关系绑定
     * @return 用户个推cid关系绑定集合
     */
    List<BizPushUserCidExcelVO> queryList(BizPushUserCidExportReq exportReq);

    /**
     * 创建用户个推cid关系绑定
     *
     * @param createReq 用户个推cid关系绑定
     * @return 结果
     */
    Boolean createBizPushUserCid(BizPushUserCidCreateReq createReq);

    /**
     * 修改用户个推cid关系绑定
     *
     * @param updateReq 用户个推cid关系绑定
     * @return 结果
     */
    Boolean updateBizPushUserCid(BizPushUserCidUpdateReq updateReq);

    /**
     * 校验并批量删除用户个推cid关系绑定信息
     *
     * @param ids     需要删除的用户个推cid关系绑定主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 获得用户个推cid关系绑定列表
     *
     * @param ids 编号
     */
    List<BizPushUserCidVO> getBizPushUserCidList(Collection<Long> ids);

}
