package com.flyhigh.form.service.impl;

import com.flyhigh.api.enums.SystemIdentification;
import com.flyhigh.api.service.FormServiceApi;
import com.flyhigh.api.vo.element.FormElementCreateReq;
import com.flyhigh.api.vo.form.FormCreateReq;
import com.flyhigh.api.vo.form.FormVO;
import com.flyhigh.api.vo.formWarehouse.FormWarehouseVO;
import com.flyhigh.form.service.IFormService;
import com.flyhigh.form.service.IFormWarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.Lai
 * @date 2023/5/8
 */
@Slf4j
@Service
public class FormServiceApiImpl implements FormServiceApi {

    @Autowired
    private IFormService formService;

    @Autowired
    private IFormWarehouseService formWarehouseService;

    /**
     * 查询表单
     *
     * @param contactId 关联ID
     * @param system    来源系统
     * @return
     */
    @Override
    public FormVO queryForm(Long contactId, SystemIdentification system) {
        return formService.queryForm(contactId, system);
    }

    /**
     * 创建表单
     * 从预设表单获取
     *
     * @param createReq
     * @return 结果
     */
    @Override
    public Boolean createFormWarehouse(List<FormCreateReq> createReq) {
        return formService.createFormWarehouse(createReq);
    }

    /**
     * 填写表单内容
     *
     * @param createReqList
     * @return
     */
    @Override
    public Boolean addFormContent(List<List<FormElementCreateReq>> createReqList) {
        return formService.addFormContent(createReqList);
    }

    /**
     * 查询表表单仓库
     *
     * @param id 表表单仓库主键
     * @return 表表单仓库
     */
    @Override
    public FormWarehouseVO queryFormWarehouseById(Long id) {
        return formWarehouseService.queryById(id);
    }

}
