package com.flyhigh.workorder.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.flyhigh.common.core.domain.entity.SysDictData;
import com.flyhigh.system.service.ISysDictTypeService;
import com.flyhigh.workorder.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 模板Service业务层处理
 *
 * @author flyhigh
 * @date 2022-05-14
 */
@Service
@Validated
public class CommonServiceImpl implements ICommonService {

    @Autowired
    private ISysDictTypeService sysDictTypeService;

    /**
     * 查询字典列表
     *
     * @param dictType 字典主键
     * @return 字典集合
     */
    @Override
    public List<String> queryDictList(String dictType) {
        List<String> list = new ArrayList<>();

        List<SysDictData> sysDictDataList = sysDictTypeService.selectDictDataByType(dictType);
        if (CollectionUtils.isNotEmpty(sysDictDataList)) {
            list = sysDictDataList.stream().map(SysDictData::getDictLabel).collect(Collectors.toList());
        }

        return list;
    }
}
