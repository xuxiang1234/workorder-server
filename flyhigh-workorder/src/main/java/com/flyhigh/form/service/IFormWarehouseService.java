package com.flyhigh.form.service;


import com.flyhigh.api.vo.formWarehouse.FormWarehouseVO;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.form.domain.entity.FormWarehouse;
import com.flyhigh.api.vo.formWarehouse.FormWarehouseCreateReq;
import com.flyhigh.api.vo.formWarehouse.FormWarehousePageReq;
import com.flyhigh.api.vo.formWarehouse.FormWarehouseUpdateReq;

import java.util.List;

/**
 * 表表单仓库Service接口
 *
 * @author flyhigh
 * @date 2022-08-06
 */
public interface IFormWarehouseService {

    /**
     * 查询表表单仓库
     *
     * @param id 表表单仓库主键
     * @return 表表单仓库
     */
    FormWarehouseVO queryById(Long id);

    /**
     * 查询表表单仓库列表
     *
     * @param pageVO 表表单仓库
     * @return 表表单仓库集合
     */
    TableDataInfo<FormWarehouseVO> queryPageList(FormWarehousePageReq pageVO);

    /**
     * 查询表单仓库列表
     *
     * @param formWarehouse 查询条件
     * @return 结果
     */
    List<FormWarehouseVO> queryList(FormWarehouse formWarehouse);


    /**
     * 创建表表单仓库
     *
     * @param createReq 表表单仓库
     * @return 结果
     */
    Boolean createFormWarehouse(FormWarehouseCreateReq createReq);

    /**
     * 修改表表单仓库
     *
     * @param updateReq 表表单仓库
     * @return 结果
     */
    Boolean updateFormWarehouse(FormWarehouseUpdateReq updateReq);

    /**
     * 删除表单仓库信息
     *
     * @param id 需要删除的表表单仓库主键
     * @return 结果
     */
    Boolean deleteById(Long id);

}
