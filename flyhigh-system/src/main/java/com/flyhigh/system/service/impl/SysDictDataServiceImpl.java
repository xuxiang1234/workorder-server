package com.flyhigh.system.service.impl;

import com.flyhigh.common.core.domain.entity.SysDictData;
import com.flyhigh.common.exception.ServiceException;
import com.flyhigh.common.utils.BeanCopyUtils;
import com.flyhigh.common.utils.DictUtils;
import com.flyhigh.common.utils.SecurityUtils;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.common.utils.bean.BeanUtils;
import com.flyhigh.system.domain.vo.dictData.AddDictDataRequest;
import com.flyhigh.system.domain.vo.dictData.EditDictDataRequest;
import com.flyhigh.system.domain.vo.dictData.QueryDictDataPageRequest;
import com.flyhigh.system.mapper.SysDictDataMapper;
import com.flyhigh.system.service.ISysDictDataService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典 业务层处理
 *
 * @author flyhigh
 */
@Service
@Slf4j
public class SysDictDataServiceImpl implements ISysDictDataService {
    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 根据条件分页查询字典数据
     *
     * @param req 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataList(QueryDictDataPageRequest req) {
        SysDictData dbQuery = new SysDictData();
        BeanCopyUtils.copy(req, dbQuery);
        PageHelper.startPage(req.getPageNum(), req.getPageSize(), req.getOrderByColumn());
        return dictDataMapper.selectDictDataList(dbQuery);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        return dictDataMapper.selectDictLabel(dictType, dictValue);
    }

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictData selectDictDataById(Long dictCode) {
        return dictDataMapper.selectDictDataById(dictCode);
    }

    /**
     * 批量删除字典数据信息
     *
     * @param dictCodes 需要删除的字典数据ID
     * @return 结果
     */
    @Override
    public void deleteDictDataByIds(List<Long> dictCodes) {
        for (Long dictCode : dictCodes) {
            SysDictData data = selectDictDataById(dictCode);
            // 校验是否有子级字典
            int count = dictDataMapper.selectCountByParentDictCode(dictCode);
            if (count > 0) {
                throw new ServiceException(String.format("%1$s存在子字典,不能删除", data.getDictLabel()));
            }
            dictDataMapper.deleteDictDataById(dictCode);
            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
    }

    /**
     * 新增保存字典数据信息
     *
     * @param req 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(AddDictDataRequest req) {
        SysDictData data = new SysDictData();
        BeanUtils.copyBeanProp(data, req);
        // 创建者
        data.setCreateBy(SecurityUtils.getUsername());
        // 计算层级，祖级等
        computeLevelAndAncestor(data);

        int row = dictDataMapper.insertDictData(data);
        if (row > 0) {
            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
        return row;
    }

    /**
     * 修改保存字典数据信息
     *
     * @param req 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(EditDictDataRequest req) {
        SysDictData data = new SysDictData();
        BeanCopyUtils.copy(req, data);
        data.setUpdateBy(SecurityUtils.getUsername());
        // 计算层级，祖级等
        computeLevelAndAncestor(data);
        int row = dictDataMapper.updateDictData(data);
        if (row > 0) {
            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
        return row;
    }

    /**
     * 计算层级和祖级id
     *
     * @param data
     */
    private void computeLevelAndAncestor(SysDictData data) {
        Long parentId = data.getParentDictCode();
        if (parentId == null) {
            data.setParentDictCode(0L);
            data.setLevel(1);
            data.setAncestorDictCodes(StringUtils.EMPTY);
            return;
        }
        SysDictData parentDict = dictDataMapper.selectDictDataById(parentId);
        if (parentDict == null) {
            log.warn("计算层级和祖级id时，根据parentId参数查不到数据：data={}", data);
            data.setParentDictCode(0L);
            data.setLevel(1);
            data.setAncestorDictCodes(StringUtils.EMPTY);
            return;
        }
        data.setLevel(parentDict.getLevel() + 1);
        String parentAncestorDictCode = parentDict.getAncestorDictCodes();
        if (StringUtils.isBlank(parentAncestorDictCode)) {
            data.setAncestorDictCodes(String.valueOf(parentDict.getDictCode()));
        } else {
            data.setAncestorDictCodes(parentAncestorDictCode + "," + parentDict.getDictCode());
        }
    }
}
