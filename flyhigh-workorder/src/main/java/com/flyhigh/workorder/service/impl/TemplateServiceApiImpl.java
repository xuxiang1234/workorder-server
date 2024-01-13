package com.flyhigh.workorder.service.impl;

import com.flyhigh.api.service.TemplateServiceApi;
import com.flyhigh.api.vo.template.TemplateSimplifyVO;
import com.flyhigh.workorder.service.ITemplateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Mr.Lai
 * @date 2023/4/21
 */
@Slf4j
@Service
public class TemplateServiceApiImpl implements TemplateServiceApi {

    @Autowired
    private ITemplateService templateService;

    /**
     * 查询模板列表 - 简化接口
     *
     * @return 模板集合
     */
    @Override
    public List<TemplateSimplifyVO> simplifyPageList() {
        return Optional.ofNullable(templateService.simplifyPageList(null)).orElse(Lists.newArrayList());
    }
}
