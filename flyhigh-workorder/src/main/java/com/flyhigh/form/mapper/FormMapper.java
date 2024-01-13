package com.flyhigh.form.mapper;

import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.form.domain.entity.Form;
import org.apache.ibatis.annotations.Param;


/**
 * 单Mapper接口
 *
 * @author flyhigh
 * @date 2022-05-29
 */
public interface FormMapper extends BaseMapperPlus<FormMapper, Form> {

    /**
     * 查询表单
     *
     * @param contactId
     * @param system
     * @return
     */
    Form selectForm(@Param("contactId") Long contactId, @Param("system") String system);

    /**
     * 新增单
     *
     * @param form 单
     * @return 结果
     */
    int insertForm(Form form);

    /**
     * 修改单
     *
     * @param form 单
     * @return 结果
     */
    int updateForm(Form form);

}
