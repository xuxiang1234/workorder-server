package com.flyhigh.api.service;

import com.flyhigh.api.vo.template.TemplateSimplifyVO;

import java.util.List;

/**
 * 工单模板
 *
 * @author Mr.Lai
 * @date 2023/3/22
 */
public interface TemplateServiceApi {

    /**
     * 查询模板列表 - 简化接口
     *
     * @return 模板集合
     */
    List<TemplateSimplifyVO> simplifyPageList();

}
