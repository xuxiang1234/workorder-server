package com.flyhigh.workorder.service;

import com.flyhigh.api.vo.template.TemplatePageReq;
import com.flyhigh.api.vo.template.TemplateSimplifyVO;
import com.flyhigh.api.vo.template.TemplateVO;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.workorder.domain.entity.TemplateProcess;
import com.flyhigh.workorder.domain.vo.template.TemplateCreateReq;
import com.flyhigh.workorder.domain.vo.template.TemplateUpdateReq;

import java.util.List;

/**
 * 模板Service接口
 *
 * @author flyhigh
 * @date 2022-05-14
 */
public interface ITemplateService {

    /**
     * 查询模板
     *
     * @param id 模板主键
     * @return 模板
     */
    TemplateVO queryById(Long id);

    /**
     * 查询模板列表
     *
     * @param pageVO 模板
     * @return 模板集合
     */
    TableDataInfo<TemplateVO> queryPageList(TemplatePageReq pageVO);

    /**
     * 查询模板列表 - 简化接口
     *
     * @param templateName 模板名称
     * @return 模板集合
     */
    List<TemplateSimplifyVO> simplifyPageList(String templateName);

    /**
     * 查询模板总数
     *
     * @return total
     */
    Long queryTotal();

    /**
     * 创建模板
     *
     * @param createReq 模板
     * @return 结果
     */
    Boolean createTemplate(TemplateCreateReq createReq);

    /**
     * 修改模板
     *
     * @param updateReq 模板
     * @return 结果
     */
    Boolean updateTemplate(TemplateUpdateReq updateReq);

    /**
     * 根据模板编号查询模板流程
     *
     * @param templateId
     * @return
     */
    List<TemplateProcess> queryTemplateProcessList(Long templateId);

    /**
     * 删除模板
     *
     * @param id 需要删除的表表单仓库主键
     * @return 结果
     */
    Boolean deleteById(Long id);

}
