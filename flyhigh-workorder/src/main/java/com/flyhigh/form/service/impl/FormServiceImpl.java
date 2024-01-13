package com.flyhigh.form.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.flyhigh.common.exception.ErrorCodeConstants;
import com.flyhigh.common.exception.ServiceExceptionUtil;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.form.domain.entity.FormWarehouse;
import com.flyhigh.form.domain.convert.FormConvert;
import com.flyhigh.form.domain.convert.FormElementConvert;
import com.flyhigh.form.domain.entity.Form;
import com.flyhigh.form.domain.entity.FormElement;
import com.flyhigh.form.domain.entity.FormElementValue;
import com.flyhigh.form.domain.entity.FormTemplate;
import com.flyhigh.form.domain.enums.FormBizName;
import com.flyhigh.api.enums.SystemIdentification;
import com.flyhigh.api.vo.element.FormElementCreateReq;
import com.flyhigh.api.vo.element.FormElementVO;
import com.flyhigh.api.vo.form.AddUserFormCreateReq;
import com.flyhigh.api.vo.form.FormCreateReq;
import com.flyhigh.api.vo.form.FormVO;
import com.flyhigh.form.mapper.*;
import com.flyhigh.form.service.IFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 单Service业务层处理
 *
 * @author flyhigh
 * @date 2022-05-29
 */
@Service
@Validated
@RequiredArgsConstructor
public class FormServiceImpl implements IFormService {

    private final FormTemplateMapper formTemplateMapper;

    private final FormMapper formMapper;

    private final FormElementMapper formElementMapper;

    private final FormElementValueMapper formElementValueMapper;

    private final FormWarehouseMapper formWarehouseMapper;

    /**
     * 新增表单
     *
     * @param createReq
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createForm(List<FormCreateReq> createReq) {
        if (CollectionUtils.isEmpty(createReq)) {
            return true;
        }

        List<Form> list = new ArrayList<>();

        for (FormCreateReq formReq : createReq) {
            Form form = FormConvert.INSTANCE.convert(formReq);

            //查找表单模板内容
            FormTemplate formtemplate = formTemplateMapper.selectForm(formReq.getFormTemplateContactId(), form.getSystem());
            if (null != formtemplate) {
                form.setIsMultForm(formtemplate.getIsMultForm());
                form.setContent(formtemplate.getContent());
                list.add(form);

            }
        }

        formMapper.insertBatch(list);
        return true;
    }

    /**
     * 创建表单
     * 从预设表单获取
     *
     * @param createReq
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createFormWarehouse(List<FormCreateReq> createReq) {
        if (CollectionUtils.isEmpty(createReq)) {
            return true;
        }
        List<Form> list = new ArrayList<>();
        for (FormCreateReq formReq : createReq) {
            Form form = FormConvert.INSTANCE.convert(formReq);
            //查找表单模板内容
            FormWarehouse formWarehouse = formWarehouseMapper.selectForm(formReq.getFormTemplateContactId(), form.getSystem());
            if (null != formWarehouse) {
                form.setIsMultForm(formWarehouse.getIsMultForm());
                form.setContent(formWarehouse.getContent());
                list.add(form);
            }
        }
        formMapper.insertBatch(list);
        return true;
    }


    /**
     * 指定人新增表单
     *
     * @param createReq
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addUserCreateForm(AddUserFormCreateReq createReq) {

        Form form = FormConvert.INSTANCE.convert(createReq);

        //查找表单模板内容
        FormWarehouse formWarehouse = formWarehouseMapper.selectById(createReq.getFormWarehouseId());
        if (null != formWarehouse) {
            form.setContent(formWarehouse.getContent());
            form.setIsMultForm(formWarehouse.getIsMultForm());
            formMapper.insert(form);
        }

        return true;
    }

    /**
     * 查询表单是否存在
     *
     * @param contactIds
     * @param system
     * @return
     */
    @Override
    public List<Long> queryFormExist(List<Long> contactIds, SystemIdentification system) {
        return formElementMapper.selectFormExist(contactIds, system.name());
    }

    /**
     * 查询表单
     *
     * @param contactId 关联ID
     * @param system    来源系统
     * @return
     */
    @Override
    public FormVO queryForm(Long contactId, SystemIdentification system) {
        Form form = formMapper.selectForm(contactId, system.name());
        if (null == form) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }
        FormVO formVO = FormConvert.INSTANCE.convert(form);

        //查询元素及值
        List<FormElementVO> formElementVOList = formElementMapper.selectFormElement(form.getId());
        if (CollectionUtils.isNotEmpty(formElementVOList)) {
            formElementVOList.forEach(e -> e.setValueString(StringUtils.join(e.getValues(), "%x%")));

            //转成前端需要的二维集合
            Collection<List<FormElementVO>> coll = formElementVOList.stream().
                    collect(Collectors.groupingBy(FormElementVO::getFormIndex, Collectors.toList())).values();
            formVO.setFormElementVOList(new ArrayList<>(coll));
        }

        return formVO;
    }


    /**
     * 查询统计表单
     *
     * @param contactIds  关联ID
     * @param formBizName 统计字段
     * @param system      来源系统
     */
    @Override
    public List<FormElementVO> queryStatisticsForm(List<Long> contactIds, FormBizName formBizName, SystemIdentification system) {
        //查询元素及值
        List<FormElementVO> formElementVOList = formElementMapper.selectStatisticsForm(contactIds, formBizName, system);

        if (CollectionUtils.isNotEmpty(formElementVOList)) {
            formElementVOList.forEach(e -> e.setValueString(StringUtils.join(e.getValues(), "%x%")));
        }
        return formElementVOList;
    }


    /**
     * 填写表单内容
     *
     * @param createReqList
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addFormContent(List<List<FormElementCreateReq>> createReqList) {
        if (CollectionUtils.isEmpty(createReqList)) {
            return true;
        }

        //保存元素值
        List<FormElementValue> formElementValueList = new ArrayList<>();
        for (int h = 0; h < createReqList.size(); h++) {

            for (FormElementCreateReq createReq : createReqList.get(h)) {

                if (CollectionUtils.isEmpty(createReq.getValues())) {
                    continue;
                }
                //保存元素
                FormElement formElement = FormElementConvert.INSTANCE.convert(createReq);
                formElement.setFormIndex(h);
                if (StringUtils.isNotBlank(formElement.getBizKey())) {
                    formElement.setBizKey(h + formElement.getBizKey());
                }
                formElementMapper.insert(formElement);

                for (int i = 0; i < createReq.getValues().size(); i++) {
                    FormElementValue formElementValue = new FormElementValue();
                    formElementValue.setFormElementId(formElement.getId());
                    formElementValue.setValueIndex(i);
                    formElementValue.setValue(createReq.getValues().get(i));
                    formElementValue.setIsNumber(StringUtils.isNumeric(createReq.getValues().get(i)) ? 1 : 0);
                    formElementValueList.add(formElementValue);
                }
            }
        }

        formElementValueMapper.insertBatch(formElementValueList);
        return true;
    }

}
