package com.flyhigh.form.service;

import com.flyhigh.form.domain.enums.FormBizName;
import com.flyhigh.api.enums.SystemIdentification;
import com.flyhigh.api.vo.element.FormElementCreateReq;
import com.flyhigh.api.vo.element.FormElementVO;
import com.flyhigh.api.vo.form.AddUserFormCreateReq;
import com.flyhigh.api.vo.form.FormCreateReq;
import com.flyhigh.api.vo.form.FormVO;

import java.util.List;

/**
 * 单Service接口
 *
 * @author flyhigh
 * @date 2022-05-29
 */
public interface IFormService {

    /**
     * 创建表单
     *
     * @param createReq
     * @return 结果
     */
    Boolean createForm(List<FormCreateReq> createReq);

    /**
     * 创建表单
     * 从预设表单获取
     *
     * @param createReq
     * @return 结果
     */
    Boolean createFormWarehouse(List<FormCreateReq> createReq);


    /**
     * 指定人新增表单
     *
     * @param createReq
     * @return
     */
    Boolean addUserCreateForm(AddUserFormCreateReq createReq);

    /**
     * 查询表单
     *
     * @param contactId 关联ID
     * @param system    来源系统
     * @return
     */
    FormVO queryForm(Long contactId, SystemIdentification system);

    /**
     * 查询统计表单
     *
     * @param contactIds  关联ID
     * @param formBizName 统计字段
     * @param system      来源系统
     * @return
     */
    List<FormElementVO> queryStatisticsForm(List<Long> contactIds, FormBizName formBizName, SystemIdentification system);

    /**
     * 填写表单内容
     *
     * @param createReqList
     * @return
     */
    Boolean addFormContent(List<List<FormElementCreateReq>> createReqList);


    /**
     * 查询表单是否存在
     *
     * @param contactIds
     * @param system
     * @return
     */
    List<Long> queryFormExist(List<Long> contactIds, SystemIdentification system);

}
