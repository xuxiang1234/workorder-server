package com.flyhigh.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.exception.ErrorCodeConstants;
import com.flyhigh.common.exception.ServiceExceptionUtil;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.sms.domain.BizPushUserAlias;
import com.flyhigh.sms.domain.convert.BizPushUserAliasConvert;
import com.flyhigh.sms.domain.vo.pushUserAlias.*;
import com.flyhigh.sms.mapper.BizPushUserAliasMapper;
import com.flyhigh.sms.service.IBizPushUserAliasService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 用户个推别名Service业务层处理
 *
 * @author flyhigh
 * @date 2022-03-31
 */
@Service
@Validated
public class BizPushUserAliasServiceImpl implements IBizPushUserAliasService {

    @Resource
    private BizPushUserAliasMapper bizPushUserAliasMapper;

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(BizPushUserAlias entity) {
        // 做一些数据校验,如唯一约束
    }

    /**
     * 校验是否存在
     */
    private void validateBizPushUserAliasExists(Long id) {
        if (bizPushUserAliasMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.SYSTEM_ERROR);
        }
    }

    /**
     * 查询用户个推别名
     *
     * @param id 用户个推别名主键
     * @return 用户个推别名
     */
    @Override
    public BizPushUserAliasVO queryById(Long id) {
        BizPushUserAlias bizPushUserAlias = bizPushUserAliasMapper.selectById(id);
        return BizPushUserAliasConvert.INSTANCE.convert(bizPushUserAlias);
    }

    /**
     * 查询用户个推别名列表
     *
     * @param pageReq 用户个推别名
     * @return 用户个推别名
     */
    @Override
    public TableDataInfo<BizPushUserAliasVO> queryPageList(BizPushUserAliasPageReq pageReq) {
        TableDataInfo<BizPushUserAlias> bizPushUserAliasTableDataInfo = bizPushUserAliasMapper.selectPage(pageReq);
        return BizPushUserAliasConvert.INSTANCE.convertPage(bizPushUserAliasTableDataInfo);
    }

    /**
     * 查询用户个推别名列表
     *
     * @param exportReq 用户个推别名
     * @return 用户个推别名
     */
    @Override
    public List<BizPushUserAliasExcelVO> queryList(BizPushUserAliasExportReq exportReq) {
        List<BizPushUserAlias> bizPushUserAliasLists = bizPushUserAliasMapper.selectList(exportReq);
        return BizPushUserAliasConvert.INSTANCE.convertListExcel(bizPushUserAliasLists);
    }

    /**
     * 构建原生mybatis-plus 查询wrapper
     *
     * @return
     */
    private LambdaQueryWrapper<BizPushUserAlias> buildQueryWrapper(BizPushUserAlias bizPushUserAlias) {
        LambdaQueryWrapper<BizPushUserAlias> lqw = Wrappers.lambdaQuery();
        lqw.eq(bizPushUserAlias.getUserId() != null, BizPushUserAlias::getUserId, bizPushUserAlias.getUserId());
        lqw.eq(StringUtils.isNotBlank(bizPushUserAlias.getUserAlias()), BizPushUserAlias::getUserAlias, bizPushUserAlias.getUserAlias());
        return lqw;
    }

    /**
     * 新增用户个推别名
     *
     * @param reateReq 用户个推别名
     * @return 结果
     */
    @Override
    public Boolean createBizPushUserAlias(BizPushUserAliasCreateReq reateReq) {
        BizPushUserAlias bizPushUserAlias = BizPushUserAliasConvert.INSTANCE.convert(reateReq);
        validEntityBeforeSave(bizPushUserAlias);
        bizPushUserAliasMapper.insert(bizPushUserAlias);
        return bizPushUserAlias.getId() > 0;
    }

    /**
     * 修改用户个推别名
     *
     * @param updateReq 用户个推别名
     * @return 结果
     */
    @Override
    public Boolean updateBizPushUserAlias(BizPushUserAliasUpdateReq updateReq) {
        // 校验存在
        validateBizPushUserAliasExists(updateReq.getId());
        // 更新
        BizPushUserAlias updateObj = BizPushUserAliasConvert.INSTANCE.convert(updateReq);
        validEntityBeforeSave(updateObj);
        return bizPushUserAliasMapper.updateById(updateObj) > 0;
    }

    /**
     * 批量删除用户个推别名
     *
     * @param ids 需要删除的用户个推别名主键
     * @return 结果
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 做一些业务上的校验,判断是否需要校验
        }
        return bizPushUserAliasMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 通过ids批量查询用户个推别名
     *
     * @param ids 需要查询的用户个推别名主键
     * @return 结果
     */
    @Override
    public List<BizPushUserAliasVO> getBizPushUserAliasList(Collection<Long> ids) {
        List<BizPushUserAlias> bizPushUserAliasLists = bizPushUserAliasMapper.selectBatchIds(ids);
        return BizPushUserAliasConvert.INSTANCE.convertList(bizPushUserAliasLists);
    }
}
