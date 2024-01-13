package com.flyhigh.form.mapper;

import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.form.domain.entity.FormTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 单模板Mapper接口
 *
 * @author flyhigh
 * @date 2022-06-02
 */
public interface FormTemplateMapper extends BaseMapperPlus<FormTemplateMapper, FormTemplate> {

    /**
     * 查询表单
     *
     * @param contactId
     * @param system
     * @return
     */
    FormTemplate selectForm(@Param("contactId") Long contactId, @Param("system") String system);

    /**
     * 批量查询表单
     *
     * @param contactIdList
     * @param system
     * @return
     */
    List<FormTemplate> selectFormBycontactIdList(@Param("contactIdList") List<Long> contactIdList, @Param("system") String system);

    /**
     * 查询单模板
     *
     * @param id 单模板主键
     * @return 单模板
     */
    FormTemplate selectFormtemplateById(Long id);

    /**
     * 查询单模板列表
     *
     * @param formtemplate 单模板
     * @return 单模板集合
     */
    List<FormTemplate> selectFormtemplateList(FormTemplate formtemplate);

    /**
     * 新增单模板
     *
     * @param formtemplate 单模板
     * @return 结果
     */
    int insertFormtemplate(FormTemplate formtemplate);

    /**
     * 修改单模板
     *
     * @param formtemplate 单模板
     * @return 结果
     */
    int updateFormtemplate(FormTemplate formtemplate);

    /**
     * 删除单模板
     *
     * @param id 单模板主键
     * @return 结果
     */
    int deleteFormtemplateById(Long id);

    /**
     * 批量删除单模板
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteFormtemplateByIds(Long[] ids);

    void deleteByContactIdAndSystem(@Param("contactId") Long contactId, @Param("system") String system);

}
