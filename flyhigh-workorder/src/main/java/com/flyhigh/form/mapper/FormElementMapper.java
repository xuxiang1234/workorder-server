package com.flyhigh.form.mapper;

import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.form.domain.entity.FormElement;
import com.flyhigh.form.domain.enums.FormBizName;
import com.flyhigh.api.enums.SystemIdentification;
import com.flyhigh.api.vo.element.FormElementVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 表单元素Mapper接口
 *
 * @author flyhigh
 * @date 2022-05-29
 */
public interface FormElementMapper extends BaseMapperPlus<FormElementMapper, FormElement> {

    /**
     * 查询表单元素
     *
     * @param id 表单元素主键
     * @return 表单元素
     */
    FormElement selectFormElementById(Long id);

    /**
     * 查询表单元素列表
     *
     * @param formElement 表单元素
     * @return 表单元素集合
     */
    List<FormElement> selectFormElementList(FormElement formElement);

    /**
     * 新增表单元素
     *
     * @param formElement 表单元素
     * @return 结果
     */
    int insertFormElement(FormElement formElement);

    /**
     * 修改表单元素
     *
     * @param formElement 表单元素
     * @return 结果
     */
    int updateFormElement(FormElement formElement);

    /**
     * 删除表单元素
     *
     * @param id 表单元素主键
     * @return 结果
     */
    int deleteFormElementById(Long id);

    /**
     * 批量删除表单元素
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteFormElementByIds(Long[] ids);


    /**
     * 查询元素及值
     *
     * @param formId
     * @return
     */
    List<FormElementVO> selectFormElement(Long formId);


    /**
     * 查询统计表单
     *
     * @param contactIds  关联ID
     * @param formBizName 统计字段
     * @param system      来源系统
     */
    List<FormElementVO> selectStatisticsForm(@Param("contactIds") List<Long> contactIds, @Param("formBizName") FormBizName formBizName, @Param("system") SystemIdentification system);


    /**
     * 查询表单是否存在
     *
     * @param contactIds
     * @param system
     * @return
     */
    List<Long> selectFormExist(@Param("contactIds") List<Long> contactIds, @Param("system") String system);

}
