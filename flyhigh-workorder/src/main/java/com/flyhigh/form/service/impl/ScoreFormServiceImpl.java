package com.flyhigh.form.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.SysConfigEnum;
import com.flyhigh.common.exception.ErrorCodeConstants;
import com.flyhigh.common.exception.ServiceException;
import com.flyhigh.common.exception.ServiceExceptionUtil;
import com.flyhigh.form.domain.entity.FormWarehouse;
import com.flyhigh.form.domain.enums.FormCategoryEnum;
import com.flyhigh.api.enums.SystemIdentification;
import com.flyhigh.api.vo.formWarehouse.FormWarehousePageReq;
import com.flyhigh.api.vo.formWarehouse.ScoreFormPageReq;
import com.flyhigh.api.vo.formWarehouse.ScoreFormVO;
import com.flyhigh.form.mapper.FormWarehouseMapper;
import com.flyhigh.form.service.IScoreFormService;
import com.flyhigh.system.service.ISysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评分表单
 *
 * @author Mr.Lai
 * @date 2023/5/5
 */
@Slf4j
@Service
public class ScoreFormServiceImpl implements IScoreFormService {

    @Resource
    private FormWarehouseMapper formWarehouseMapper;

    @Autowired
    private ISysConfigService configService;

    /**
     * 查询评分表单列表
     *
     * @param pageReq
     * @return
     */
    @Override
    public TableDataInfo<ScoreFormVO> queryPageList(ScoreFormPageReq pageReq) {
        Page<ScoreFormVO> page = pageReq.build();

        FormWarehousePageReq formWarehousePageReq = BeanUtil.copyProperties(pageReq, FormWarehousePageReq.class);
        formWarehousePageReq.setSystem(SystemIdentification.OPERATIONAL_SCORE.name());
        List<FormWarehouse> list = formWarehouseMapper.selectFormWarehousePage(page, formWarehousePageReq);

        List<ScoreFormVO> scoreFormList = BeanUtil.copyToList(list, ScoreFormVO.class);

        List<String> scoreFormIds = this.getActivateFormIds();

        // 评分表单激活状态
        scoreFormList.forEach(scoreFormVO -> scoreFormVO.setIsActive(scoreFormIds.contains(String.valueOf(scoreFormVO.getId()))));

        page.setRecords(scoreFormList);

        return TableDataInfo.build(page);
    }

    /**
     * 激活评分表单
     *
     * @param id 表单ID
     * @return 激活结果
     */
    @Override
    public Boolean activateForm(Long id) {
        FormWarehouse formWarehouse = formWarehouseMapper.selectById(id);
        if (null == formWarehouse || !SystemIdentification.OPERATIONAL_SCORE.name().equals(formWarehouse.getSystem())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }

        List<String> scoreFormIds = this.getActivateFormIds();
        if (scoreFormIds.contains(String.valueOf(id))) {
            throw new ServiceException("已激活");
        }

        Map<String, String> configMap = new HashMap<>(2);
        configMap.put(FormCategoryEnum.LEASE_UNIT_SCORE_FORM.getType(), SysConfigEnum.OPERATIONAL_LEASE_UNIT_SCORE_FORM.getKey());
        configMap.put(FormCategoryEnum.TENANT_SCORE_FORM.getType(), SysConfigEnum.OPERATIONAL_TENANT_SCORE_FORM.getKey());

        String configKey = configMap.get(formWarehouse.getCategory());

        if (StringUtils.isBlank(configKey)) {
            return false;
        }
        return configService.updateConfigValueByKey(configKey, String.valueOf(id)) > 0;
    }

    /**
     * 获取激活的评分表单ID列表
     *
     * @return
     */
    private List<String> getActivateFormIds() {
        // 租赁单元评分表ID
        String leaseUnitScoreFormId = configService.selectConfigByKey(SysConfigEnum.OPERATIONAL_LEASE_UNIT_SCORE_FORM.getKey());
        // 入驻商家评分表ID
        String tenantScoreFormId = configService.selectConfigByKey(SysConfigEnum.OPERATIONAL_TENANT_SCORE_FORM.getKey());
        return Arrays.asList(leaseUnitScoreFormId, tenantScoreFormId);
    }

}
