package com.flyhigh.api.service;

import com.flyhigh.api.enums.SystemIdentification;
import com.flyhigh.api.vo.element.FormElementCreateReq;
import com.flyhigh.api.vo.form.FormCreateReq;
import com.flyhigh.api.vo.form.FormVO;
import com.flyhigh.api.vo.formWarehouse.FormWarehouseVO;

import java.util.List;


public interface FormServiceApi {

    /**
     * 查询表单
     *
     * @param contactId 关联ID
     * @param system    来源系统
     * @return
     */
    FormVO queryForm(Long contactId, SystemIdentification system);

    /**
     * 创建表单
     * 从预设表单获取
     *
     * @param createReq
     * @return 结果
     */
    Boolean createFormWarehouse(List<FormCreateReq> createReq);

    /**
     * 填写表单内容
     *
     * @param createReqList
     * @return
     */
    Boolean addFormContent(List<List<FormElementCreateReq>> createReqList);

    /**
     * 查询表表单仓库
     *
     * @param id 表表单仓库主键
     * @return 表表单仓库
     */
    FormWarehouseVO queryFormWarehouseById(Long id);

}
