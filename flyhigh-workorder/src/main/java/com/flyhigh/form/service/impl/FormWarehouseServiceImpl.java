package com.flyhigh.form.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyhigh.common.core.domain.model.LoginUser;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.exception.ErrorCodeConstants;
import com.flyhigh.common.exception.GlobalErrorCodeConstants;
import com.flyhigh.common.exception.ServiceExceptionUtil;
import com.flyhigh.common.utils.SecurityUtils;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.form.domain.entity.FormWarehouse;
import com.flyhigh.form.domain.convert.FormWarehouseConvert;
import com.flyhigh.api.vo.formWarehouse.FormWarehouseCreateReq;
import com.flyhigh.api.vo.formWarehouse.FormWarehousePageReq;
import com.flyhigh.api.vo.formWarehouse.FormWarehouseUpdateReq;
import com.flyhigh.api.vo.formWarehouse.FormWarehouseVO;
import com.flyhigh.form.mapper.FormWarehouseMapper;
import com.flyhigh.form.service.IFormWarehouseService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

/**
 * 表表单仓库Service业务层处理
 *
 * @author flyhigh
 * @date 2022-08-06
 */
@Service
@Validated
public class FormWarehouseServiceImpl implements IFormWarehouseService {

    @Resource
    private FormWarehouseMapper formWarehouseMapper;

    /**
     * 校验是否存在
     */
    private void validateFormWarehouseExists(Long id) {
        if (formWarehouseMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }
    }

    /**
     * 查询表表单仓库
     *
     * @param id 表表单仓库主键
     * @return 表单仓库
     */
    @Override
    public FormWarehouseVO queryById(Long id) {
        FormWarehouse formWarehouse = formWarehouseMapper.selectById(id);
        return FormWarehouseConvert.INSTANCE.convert(formWarehouse);
    }

    /**
     * 查询表单仓库列表
     *
     * @param pageReq 表单仓库
     * @return 表单仓库
     */
    @Override
    public TableDataInfo<FormWarehouseVO> queryPageList(FormWarehousePageReq pageReq) {
        Page<FormWarehouseVO> page = pageReq.build();

        List<FormWarehouse> list = formWarehouseMapper.selectFormWarehousePage(page, pageReq);
        page.setRecords(FormWarehouseConvert.INSTANCE.convertList(list));

        return TableDataInfo.build(page);
    }

    /**
     * 查询表单仓库列表
     *
     * @param formWarehouse 查询条件
     * @return 结果
     */
    @Override
    public List<FormWarehouseVO> queryList(FormWarehouse formWarehouse) {
        List<FormWarehouse> formWarehouses = formWarehouseMapper.selectList(buildQueryWrapper(formWarehouse));
        return FormWarehouseConvert.INSTANCE.convertList(formWarehouses);
    }


    /**
     * 构建原生mybatis-plus 查询wrapper
     *
     * @return
     */
    private LambdaQueryWrapper<FormWarehouse> buildQueryWrapper(FormWarehouse formWarehouse) {
        LambdaQueryWrapper<FormWarehouse> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(formWarehouse.getName()), FormWarehouse::getName, formWarehouse.getName());
        lqw.eq(StringUtils.isNotBlank(formWarehouse.getSystem()), FormWarehouse::getSystem, formWarehouse.getSystem());
        lqw.eq(StringUtils.isNotBlank(formWarehouse.getCategory()), FormWarehouse::getCategory, formWarehouse.getCategory());
        return lqw;
    }

    /**
     * 新增表单仓库
     *
     * @param createReq 表单仓库
     * @return 结果
     */
    @Override
    public Boolean createFormWarehouse(FormWarehouseCreateReq createReq) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        FormWarehouse formWarehouse = FormWarehouseConvert.INSTANCE.convert(createReq);
        formWarehouse.setCreateId(loginUser.getUserId());
        formWarehouse.setCreateName(loginUser.getUsername());
        formWarehouseMapper.insert(formWarehouse);
        return true;
    }

    /**
     * 修改表单仓库
     *
     * @param updateReq 表单仓库
     * @return 结果
     */
    @Override
    public Boolean updateFormWarehouse(FormWarehouseUpdateReq updateReq) {
        // 校验存在
        validateFormWarehouseExists(updateReq.getId());
        // 更新
        FormWarehouse updateObj = FormWarehouseConvert.INSTANCE.convert(updateReq);
        formWarehouseMapper.updateById(updateObj);

        return true;
    }

    /**
     * 删除表单仓库
     *
     * @param id 需要删除的表单仓库主键
     * @return 结果
     */
    @Override
    public Boolean deleteById(Long id) {
        FormWarehouse formWarehouse = formWarehouseMapper.selectById(id);
        if (formWarehouse == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }

        if (!formWarehouse.getCreateId().equals(SecurityUtils.getUserId())) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.FORBIDDEN);
        }

        formWarehouseMapper.deleteById(id);
        return true;
    }

}
