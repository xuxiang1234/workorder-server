package com.flyhigh.form.mapper;

import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.form.domain.entity.FormElementValue;

import java.util.List;


/**
 * 表单元素值Mapper接口
 *
 * @author flyhigh
 * @date 2022-05-29
 */
public interface FormElementValueMapper extends BaseMapperPlus<FormElementValueMapper, FormElementValue> {


    /**
     * 查询表单元素值
     *
     * @param id 表单元素值主键
     * @return 表单元素值
     */
    FormElementValue selectFormElementValueById(Long id);

    /**
     * 查询表单元素值列表
     *
     * @param formElementValue 表单元素值
     * @return 表单元素值集合
     */
    List<FormElementValue> selectFormElementValueList(FormElementValue formElementValue);

    /**
     * 新增表单元素值
     *
     * @param formElementValue 表单元素值
     * @return 结果
     */
    int insertFormElementValue(FormElementValue formElementValue);

    /**
     * 修改表单元素值
     *
     * @param formElementValue 表单元素值
     * @return 结果
     */
    int updateFormElementValue(FormElementValue formElementValue);

    /**
     * 删除表单元素值
     *
     * @param id 表单元素值主键
     * @return 结果
     */
    int deleteFormElementValueById(Long id);

    /**
     * 批量删除表单元素值
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteFormElementValueByIds(Long[] ids);


}
