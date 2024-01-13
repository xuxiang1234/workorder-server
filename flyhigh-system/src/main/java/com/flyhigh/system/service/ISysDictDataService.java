package com.flyhigh.system.service;

import com.flyhigh.common.core.domain.entity.SysDictData;
import com.flyhigh.system.domain.vo.dictData.AddDictDataRequest;
import com.flyhigh.system.domain.vo.dictData.EditDictDataRequest;
import com.flyhigh.system.domain.vo.dictData.QueryDictDataPageRequest;

import java.util.List;

/**
 * 字典 业务层
 *
 * @author flyhigh
 */
public interface ISysDictDataService {
    /**
     * 根据条件分页查询字典数据
     *
     * @param req 字典数据信息
     * @return 字典数据集合信息
     */
    List<SysDictData> selectDictDataList(QueryDictDataPageRequest req);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    String selectDictLabel(String dictType, String dictValue);

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    SysDictData selectDictDataById(Long dictCode);

    /**
     * 批量删除字典数据信息
     *
     * @param dictCodes 需要删除的字典数据ID
     * @return 结果
     */
    void deleteDictDataByIds(List<Long> dictCodes);

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    int insertDictData(AddDictDataRequest dictData);

    /**
     * 修改保存字典数据信息
     *
     * @param req 字典数据信息
     * @return 结果
     */
    int updateDictData(EditDictDataRequest req);
}
