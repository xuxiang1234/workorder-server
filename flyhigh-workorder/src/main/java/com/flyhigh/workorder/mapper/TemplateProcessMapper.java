package com.flyhigh.workorder.mapper;


import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.workorder.domain.entity.TemplateProcess;

import java.util.List;


/**
 * 模板流程Mapper接口
 *
 * @author flyhigh
 * @date 2022-05-15
 */
public interface TemplateProcessMapper extends BaseMapperPlus<TemplateProcessMapper, TemplateProcess> {

    /**
     * 查询模板流程
     *
     * @param id 模板流程主键
     * @return 模板流程
     */
    TemplateProcess selectTemplateProcessById(Long id);

    /**
     * 查询模板流程列表
     *
     * @param templateProcess 模板流程
     * @return 模板流程集合
     */
    List<TemplateProcess> selectTemplateProcessList(TemplateProcess templateProcess);

    /**
     * 新增模板流程
     *
     * @param templateProcess 模板流程
     * @return 结果
     */
    int insertTemplateProcess(TemplateProcess templateProcess);

    /**
     * 修改模板流程
     *
     * @param templateProcess 模板流程
     * @return 结果
     */
    int updateTemplateProcess(TemplateProcess templateProcess);

    /**
     * 删除模板流程
     *
     * @param id 模板流程主键
     * @return 结果
     */
    int deletetemplateProcessById(Long id);

    /**
     * 批量删除模板流程
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deletetemplateProcessByIds(Long[] ids);

    /**
     * 删除模板流程
     *
     * @param templateId 模板编号
     * @return 结果
     */
    int deleteTemplatetemplateId(Long templateId);

}
