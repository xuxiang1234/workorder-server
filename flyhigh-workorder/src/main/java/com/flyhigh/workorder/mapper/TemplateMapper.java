package com.flyhigh.workorder.mapper;


import com.flyhigh.api.vo.template.TemplatePageReq;
import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.core.query.LambdaQueryWrapperPlus;
import com.flyhigh.workorder.domain.entity.Template;

import java.util.List;

/**
 * 模板Mapper接口
 *
 * @author flyhigh
 * @date 2022-05-14
 */
public interface TemplateMapper extends BaseMapperPlus<TemplateMapper, Template> {

    /**
     * 查询模板
     *
     * @param id 模板主键
     * @return 模板
     */
    Template selectTemplateById(Long id);

    /**
     * 查询模板列表
     *
     * @param template 模板
     * @return 模板集合
     */
    List<Template> selectTemplateList(Template template);

    /**
     * 新增模板
     *
     * @param template 模板
     * @return 结果
     */
    int insertTemplate(Template template);

    /**
     * 修改模板
     *
     * @param template 模板
     * @return 结果
     */
    int updateTemplate(Template template);

    /**
     * 删除模板
     *
     * @param id 模板主键
     * @return 结果
     */
    int deletetemplateById(Long id);

    /**
     * 批量删除模板
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deletetemplateByIds(Long[] ids);

    /**
    * 分页查询
     * @param req
     * @return
     */
    default TableDataInfo<Template> selectPage(TemplatePageReq req) {
        return selectPage(req, new LambdaQueryWrapperPlus<Template>()
        .eqIfPresent(Template::getId, req.getId())
        .likeIfPresent(Template::getTemplateName, req.getTemplateName())
        .eqIfPresent(Template::getTemplateType, req.getTemplateType())
        .eqIfPresent(Template::getDescription, req.getDescription())
        .eqIfPresent(Template::getPicUrl, req.getPicUrl())
        .eqIfPresent(Template::getCreateId, req.getCreateId())
        .likeIfPresent(Template::getCreateName, req.getCreateName())
                .orderByDesc(Template::getId));
    }


    /**
     * 查询模板总数
     *
     * @return total
     */
    long queryTotal();

    /**
     * 根据名称查询
     * @param templateName
     * @return
     */
    Template selectByName(String templateName);
}
