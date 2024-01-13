package com.flyhigh.form.service;

import com.flyhigh.api.enums.SystemIdentification;
import com.flyhigh.api.vo.formtemplate.FormTemplateCreateReq;
import com.flyhigh.api.vo.formtemplate.FormTemplateUpdateReq;
import com.flyhigh.api.vo.formtemplate.FormTemplateVO;

import java.util.List;

/**
 * 表单模板Service接口
 *
 * @author flyhigh
 * @date 2022-06-02
 */
public interface IFormTemplateService {

    /**
     * 创建表单模板
     *
     * @param createReq
     * @return 结果
     */
    Boolean createFormtemplate(List<FormTemplateCreateReq> createReq);

    /**
     * 修改表单模板
     *
     * @param updateReq
     * @return 结果
     */
    Boolean updateFormtemplate(List<FormTemplateUpdateReq> updateReq);

    /**
     * 查询表单模板
     *
     * @param contactId 关联ID
     * @param system    来源系统
     * @return
     */
    FormTemplateVO queryFormtemplate(Long contactId, SystemIdentification system);

}
