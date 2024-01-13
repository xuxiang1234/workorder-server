package com.flyhigh.system.service;

import com.flyhigh.common.core.domain.entity.SysDictData;
import com.flyhigh.common.core.domain.entity.SysDictType;
import com.flyhigh.common.core.domain.vo.ComboBoxTreeVO;
import com.flyhigh.system.domain.vo.dictType.ModifyDictTypeRequest;
import com.flyhigh.system.domain.vo.dictType.QueryDictTypeRequest;
import com.flyhigh.system.domain.vo.dictType.TreeByDictVo;
import com.google.common.collect.Table;

import java.util.List;

/**
 * 字典 业务层
 *
 * @author flyhigh
 */
public interface ISysDictTypeService {
    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    List<SysDictType> selectDictTypeList(QueryDictTypeRequest req);

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    List<SysDictType> selectDictTypeAll();

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    List<SysDictData> selectDictDataByType(String dictType);

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    List<SysDictData> selectDictDataByTypeWithCache(String dictType);

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    SysDictType selectDictTypeById(Long dictId);

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    SysDictType selectDictTypeByType(String dictType);

    /**
     * 批量删除字典信息
     *
     * @param dictIds 需要删除的字典ID
     * @return 结果
     */
    void deleteDictTypeByIds(List<Long> dictIds);

    /**
     * 加载字典缓存数据
     */
    void loadingDictCache();

    /**
     * 清空字典缓存数据
     */
    void clearDictCache();

    /**
     * 重置字典缓存数据
     */
    void resetDictCache();

    /**
     * 新增保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    int insertDictType(ModifyDictTypeRequest req);

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    int updateDictType(ModifyDictTypeRequest req);

    /**
     * 校验字典类型称是否唯一
     *
     * @param req 字典类型
     * @return 结果
     */
    String checkDictTypeUnique(ModifyDictTypeRequest req);

    /**
     * 根据字典类型查询字典数据已树形结构返回
     *
     * @param dictType
     * @return
     */
    List<TreeByDictVo> queryTreeByDictType(String dictType);

    /**
     * 多个key获取字典
     *
     * @param keys
     * @return
     */
    Table<String, String, String> getDictTable(String... keys);

    /**
     * 构建字典下拉列表
     *
     * @param sysDictData 字典数据
     * @return
     */
    List<ComboBoxTreeVO> buildDictDataTreeSelect(List<SysDictData> sysDictData);

}
