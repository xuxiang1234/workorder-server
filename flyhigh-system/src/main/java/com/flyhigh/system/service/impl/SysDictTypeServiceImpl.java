package com.flyhigh.system.service.impl;

import com.flyhigh.common.constant.UserConstants;
import com.flyhigh.common.core.domain.entity.SysDictData;
import com.flyhigh.common.core.domain.entity.SysDictType;
import com.flyhigh.common.core.domain.vo.ComboBoxTreeVO;
import com.flyhigh.common.exception.ServiceException;
import com.flyhigh.common.utils.BeanCopyUtils;
import com.flyhigh.common.utils.DictUtils;
import com.flyhigh.common.utils.SecurityUtils;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.system.domain.vo.dictType.ModifyDictTypeRequest;
import com.flyhigh.system.domain.vo.dictType.QueryDictTypeRequest;
import com.flyhigh.system.domain.vo.dictType.TreeByDictVo;
import com.flyhigh.system.mapper.SysDictDataMapper;
import com.flyhigh.system.mapper.SysDictTypeMapper;
import com.flyhigh.system.service.ISysDictTypeService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 字典 业务层处理
 *
 * @author flyhigh
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService {
    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 项目启动时，初始化字典到缓存
     */
    @PostConstruct
    public void init() {
        loadingDictCache();
    }

    /**
     * 根据条件分页查询字典类型
     *
     * @param req 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeList(QueryDictTypeRequest req) {
        SysDictType dbQuery = new SysDictType();
        BeanCopyUtils.copy(req, dbQuery);
        PageHelper.startPage(req.getPageNum(), req.getPageSize(), req.getOrderByColumn());
        return dictTypeMapper.selectDictTypeList(dbQuery);
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeAll() {
        return dictTypeMapper.selectDictTypeAll();
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotEmpty(dictDatas)) {
            DictUtils.setDictCache(dictType, dictDatas);
            return dictDatas;
        }
        return Lists.newArrayList();
    }


    @Override
    public List<SysDictData> selectDictDataByTypeWithCache(String dictType) {
        List<SysDictData> dictDatas = DictUtils.getDictCache(dictType);
        if (StringUtils.isNotEmpty(dictDatas)) {
            return dictDatas;
        }
        dictDatas = this.selectDictDataByType(dictType);
        return dictDatas;
    }


    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeById(Long dictId) {
        return dictTypeMapper.selectDictTypeById(dictId);
    }

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeByType(String dictType) {
        return dictTypeMapper.selectDictTypeByType(dictType);
    }

    /**
     * 批量删除字典类型信息
     *
     * @param dictIds 需要删除的字典ID
     * @return 结果
     */
    @Override
    public void deleteDictTypeByIds(List<Long> dictIds) {
        for (Long dictId : dictIds) {
            SysDictType dictType = selectDictTypeById(dictId);
            if (dictDataMapper.countDictDataByType(dictType.getDictType()) > 0) {
                throw new ServiceException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
            dictTypeMapper.deleteDictTypeById(dictId);
            DictUtils.removeDictCache(dictType.getDictType());
        }
    }

    /**
     * 加载字典缓存数据
     */
    @Override
    public void loadingDictCache() {
        List<SysDictType> dictTypeList = dictTypeMapper.selectDictTypeAll();
        for (SysDictType dictType : dictTypeList) {
            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(dictType.getDictType());
            DictUtils.setDictCache(dictType.getDictType(), dictDatas);
        }
    }

    /**
     * 清空字典缓存数据
     */
    @Override
    public void clearDictCache() {
        DictUtils.clearDictCache();
    }

    /**
     * 重置字典缓存数据
     */
    @Override
    public void resetDictCache() {
        clearDictCache();
        loadingDictCache();
    }

    /**
     * 新增保存字典类型信息
     *
     * @param req 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(ModifyDictTypeRequest req) {
        SysDictType dict = new SysDictType();
        BeanCopyUtils.copy(req, dict);
        dict.setCreateBy(SecurityUtils.getUsername());
        int row = dictTypeMapper.insertDictType(dict);
        if (row > 0) {
            DictUtils.setDictCache(dict.getDictType(), null);
        }
        return row;
    }

    /**
     * 修改保存字典类型信息
     *
     * @param req 字典类型信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateDictType(ModifyDictTypeRequest req) {
        SysDictType dict = new SysDictType();
        BeanCopyUtils.copy(req, dict);
        dict.setUpdateBy(SecurityUtils.getUsername());
        SysDictType oldDict = dictTypeMapper.selectDictTypeById(dict.getDictId());
        dictDataMapper.updateDictDataType(oldDict.getDictType(), dict.getDictType());
        int row = dictTypeMapper.updateDictType(dict);
        if (row > 0) {
            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(dict.getDictType());
            DictUtils.setDictCache(dict.getDictType(), dictDatas);
        }
        return row;
    }

    /**
     * 校验字典类型称是否唯一
     *
     * @param req 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(ModifyDictTypeRequest req) {
        Long dictId = StringUtils.isNull(req.getDictId()) ? -1L : req.getDictId();
        SysDictType dictType = dictTypeMapper.checkDictTypeUnique(req.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 根据字典类型查询字典数据已树形结构返回
     *
     * @param dictType
     * @return
     */
    @Override
    public List<TreeByDictVo> queryTreeByDictType(String dictType) {
        List<SysDictData> dictCache = DictUtils.getDictCache(dictType);
        if (CollectionUtils.isEmpty(dictCache)) {
            dictCache = dictDataMapper.selectDictDataByType(dictType);
            DictUtils.setDictCache(dictType, dictCache);
        }
        if (CollectionUtils.isEmpty(dictCache)) {
            return Collections.emptyList();
        }
        Long parentId = 0l;
        return buildDictDataTree(parentId, dictCache);
    }

    /**
     * 拼接tree型数据结构
     *
     * @param parentId
     * @param sysDictDataList
     * @return
     */
    private List<TreeByDictVo> buildDictDataTree(Long parentId, List<SysDictData> sysDictDataList) {
        List<TreeByDictVo> treeByDictVos = Lists.newArrayList();
        List<SysDictData> sysDictChildren = sysDictDataList.stream().filter(data -> data.getParentDictCode().equals(parentId)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(sysDictChildren)) {
            sysDictChildren.stream().forEach((SysDictData data) -> {
                TreeByDictVo treeByDictVo = new TreeByDictVo();
                treeByDictVos.add(treeByDictVo);
                treeByDictVo.setDictId(data.getDictCode());
                treeByDictVo.setLabel(data.getDictLabel());
                treeByDictVo.setLevel(data.getLevel());
                treeByDictVo.setValue(data.getDictValue());
                treeByDictVo.setCssClass(data.getCssClass());
                treeByDictVo.setChildren(buildDictDataTree(data.getDictCode(), sysDictDataList));
            });
        }
        return treeByDictVos;
    }

    /**
     * 多个key获取字典
     *
     * @param keys
     * @return
     */
    @Override
    public Table<String, String, String> getDictTable(String... keys) {
        Table<String, String, String> table = HashBasedTable.create();
        Stream.of(keys).forEach(item -> {
            List<SysDictData> dictCache = DictUtils.getDictCache(item);
            if (CollectionUtils.isEmpty(dictCache)) {
                dictCache = dictDataMapper.selectDictDataByType(item);
                DictUtils.setDictCache(item, dictCache);
            }
            Optional.ofNullable(dictCache).ifPresent(dictCaches -> {
                dictCaches.forEach(dict -> {
                    table.put(item, dict.getDictValue(), dict.getDictLabel());
                });
            });
        });
        return table;
    }

    /**
     * 构建字典下拉列表
     *
     * @param sysDictData 字典数据
     * @return
     */
    @Override
    public List<ComboBoxTreeVO> buildDictDataTreeSelect(List<SysDictData> sysDictData) {
        if (CollectionUtils.isEmpty(sysDictData)) {
            return Lists.newArrayList();
        }
        List<ComboBoxTreeVO> list = Lists.newArrayList();
        sysDictData.forEach(item -> list.add(new ComboBoxTreeVO(item.getDictValue(), item.getDictLabel())));
        return list;
    }

}
