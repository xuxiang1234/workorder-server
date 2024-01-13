package com.flyhigh.form.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.form.domain.entity.FormWarehouse;
import com.flyhigh.api.vo.formWarehouse.FormWarehousePageReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 表表单仓库Mapper接口
 *
 * @author flyhigh
 * @date 2022-08-06
 */
public interface FormWarehouseMapper extends BaseMapperPlus<FormWarehouseMapper, FormWarehouse> {

    /**
     * 查询表单
     *
     * @param id     表单
     * @param system 系统
     * @return
     */
    FormWarehouse selectForm(@Param("id") Long id, @Param("system") String system);

    /**
     * 查询表表单仓库
     *
     * @param id 表表单仓库主键
     * @return 表表单仓库
     */
    FormWarehouse selectFormWarehouseById(Long id);

    /**
     * 查询表表单仓库列表
     *
     * @param pageReq 表表单仓库
     * @return 表表单仓库集合
     */
    List<FormWarehouse> selectFormWarehousePage(Page page, @Param("pageReq") FormWarehousePageReq pageReq);

    /**
     * 新增表表单仓库
     *
     * @param formWarehouse 表表单仓库
     * @return 结果
     */
    int insertFormWarehouse(FormWarehouse formWarehouse);

    /**
     * 修改表表单仓库
     *
     * @param formWarehouse 表表单仓库
     * @return 结果
     */
    int updateFormWarehouse(FormWarehouse formWarehouse);

}
