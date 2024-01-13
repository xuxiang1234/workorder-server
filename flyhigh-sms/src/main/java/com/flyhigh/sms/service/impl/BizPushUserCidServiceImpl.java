package com.flyhigh.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.exception.ErrorCodeConstants;
import com.flyhigh.common.exception.ServiceExceptionUtil;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.sms.domain.BizPushUserCid;
import com.flyhigh.sms.domain.convert.BizPushUserCidConvert;
import com.flyhigh.sms.domain.vo.pushUserCid.*;
import com.flyhigh.sms.mapper.BizPushUserCidMapper;
import com.flyhigh.sms.service.IBizPushUserCidService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 用户个推cid关系绑定Service业务层处理
 *
 * @author flyhigh
 * @date 2022-03-31
 */
@Service
@Validated
public class BizPushUserCidServiceImpl implements IBizPushUserCidService {

    @Resource
    private BizPushUserCidMapper bizPushUserCidMapper;

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(BizPushUserCid entity) {
        // 做一些数据校验,如唯一约束
    }

    /**
     * 校验是否存在
     */
    private void validateBizPushUserCidExists(Long id) {
        if (bizPushUserCidMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.SYSTEM_ERROR);
        }
    }

    /**
     * 查询用户个推cid关系绑定
     *
     * @param id 用户个推cid关系绑定主键
     * @return 用户个推cid关系绑定
     */
    @Override
    public BizPushUserCidVO queryById(Long id) {
        BizPushUserCid bizPushUserCid = bizPushUserCidMapper.selectById(id);
        return BizPushUserCidConvert.INSTANCE.convert(bizPushUserCid);
    }

    /**
     * 查询用户个推cid关系绑定列表
     *
     * @param pageReq 用户个推cid关系绑定
     * @return 用户个推cid关系绑定
     */
    @Override
    public TableDataInfo<BizPushUserCidVO> queryPageList(BizPushUserCidPageReq pageReq) {
        TableDataInfo<BizPushUserCid> bizPushUserCidTableDataInfo = bizPushUserCidMapper.selectPage(pageReq);
        return BizPushUserCidConvert.INSTANCE.convertPage(bizPushUserCidTableDataInfo);
    }

    /**
     * 查询用户个推cid关系绑定列表
     *
     * @param exportReq 用户个推cid关系绑定
     * @return 用户个推cid关系绑定
     */
    @Override
    public List<BizPushUserCidExcelVO> queryList(BizPushUserCidExportReq exportReq) {
        List<BizPushUserCid> bizPushUserCidLists = bizPushUserCidMapper.selectList(exportReq);
        return BizPushUserCidConvert.INSTANCE.convertListExcel(bizPushUserCidLists);
    }

    /**
     * 构建原生mybatis-plus 查询wrapper
     *
     * @return
     */
    private LambdaQueryWrapper<BizPushUserCid> buildQueryWrapper(BizPushUserCid bizPushUserCid) {
        LambdaQueryWrapper<BizPushUserCid> lqw = Wrappers.lambdaQuery();
        lqw.eq(bizPushUserCid.getUserId() != null, BizPushUserCid::getUserId, bizPushUserCid.getUserId());
        lqw.eq(StringUtils.isNotBlank(bizPushUserCid.getCid()), BizPushUserCid::getCid, bizPushUserCid.getCid());
        lqw.eq(StringUtils.isNotBlank(bizPushUserCid.getUserAlias()), BizPushUserCid::getUserAlias, bizPushUserCid.getUserAlias());
        return lqw;
    }

    /**
     * 新增用户个推cid关系绑定
     *
     * @param reateReq 用户个推cid关系绑定
     * @return 结果
     */
    @Override
    public Boolean createBizPushUserCid(BizPushUserCidCreateReq reateReq) {
        BizPushUserCid bizPushUserCid = BizPushUserCidConvert.INSTANCE.convert(reateReq);
        validEntityBeforeSave(bizPushUserCid);
        bizPushUserCidMapper.insert(bizPushUserCid);
        return bizPushUserCid.getId() > 0;
    }

    /**
     * 修改用户个推cid关系绑定
     *
     * @param updateReq 用户个推cid关系绑定
     * @return 结果
     */
    @Override
    public Boolean updateBizPushUserCid(BizPushUserCidUpdateReq updateReq) {
        // 校验存在
        validateBizPushUserCidExists(updateReq.getId());
        // 更新
        BizPushUserCid updateObj = BizPushUserCidConvert.INSTANCE.convert(updateReq);
        validEntityBeforeSave(updateObj);
        return bizPushUserCidMapper.updateById(updateObj) > 0;
    }

    /**
     * 批量删除用户个推cid关系绑定
     *
     * @param ids 需要删除的用户个推cid关系绑定主键
     * @return 结果
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 做一些业务上的校验,判断是否需要校验
        }
        return bizPushUserCidMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 通过ids批量查询用户个推cid关系绑定
     *
     * @param ids 需要查询的用户个推cid关系绑定主键
     * @return 结果
     */
    @Override
    public List<BizPushUserCidVO> getBizPushUserCidList(Collection<Long> ids) {
        List<BizPushUserCid> bizPushUserCidLists = bizPushUserCidMapper.selectBatchIds(ids);
        return BizPushUserCidConvert.INSTANCE.convertList(bizPushUserCidLists);
    }
}
