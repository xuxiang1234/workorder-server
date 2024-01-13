package com.flyhigh.form.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.flyhigh.form.domain.convert.FormTemplateConvert;
import com.flyhigh.form.domain.entity.FormTemplate;
import com.flyhigh.api.enums.SystemIdentification;
import com.flyhigh.api.vo.formtemplate.FormTemplateCreateReq;
import com.flyhigh.api.vo.formtemplate.FormTemplateUpdateReq;
import com.flyhigh.api.vo.formtemplate.FormTemplateVO;
import com.flyhigh.form.mapper.FormTemplateMapper;
import com.flyhigh.form.service.IFormTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 表单模板Service业务层处理
 *
 * @author flyhigh
 * @date 2022-06-02
 */
@Service
@Validated
@RequiredArgsConstructor
@Slf4j
public class FormTemplateServiceImpl implements IFormTemplateService {

    private final FormTemplateMapper formtemplateMapper;

    /**
     * 新增表单模板
     *
     * @param reateReq
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createFormtemplate(List<FormTemplateCreateReq> reateReq) {
        if (CollectionUtils.isEmpty(reateReq)) {
            return true;
        }

        List<FormTemplate> formTemplateList = FormTemplateConvert.INSTANCE.convert(reateReq);
        formtemplateMapper.insertBatch(formTemplateList);
        return true;
    }

    /**
     * 修改表单模板
     *
     * @param updateReq 单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateFormtemplate(List<FormTemplateUpdateReq> updateReq) {
        if (CollectionUtils.isEmpty(updateReq)) {
            return true;
        }

        // 更新
        List<FormTemplate> formTemplateList = FormTemplateConvert.INSTANCE.convertUpdateList(updateReq);
        for (FormTemplate formTemplate : formTemplateList) {
            formtemplateMapper.deleteByContactIdAndSystem(formTemplate.getContactId(), formTemplate.getSystem());

            if (StringUtils.isNotBlank(formTemplate.getContent())) {
                formtemplateMapper.insert(formTemplate);
            }
        }
        return true;
    }

    /**
     * 查询表单模板
     *
     * @param contactId 关联ID
     * @param system    来源系统
     * @return
     */
    @Override
    public FormTemplateVO queryFormtemplate(Long contactId, SystemIdentification system) {
        FormTemplate formTemplate = formtemplateMapper.selectForm(contactId, system.name());
        return FormTemplateConvert.INSTANCE.convert(formTemplate);
    }

}
