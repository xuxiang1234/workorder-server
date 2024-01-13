package com.flyhigh.system.service.impl;

import com.flyhigh.common.utils.DictUtils;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.api.service.DictTypeServiceApi;
import com.flyhigh.system.service.ISysDictTypeService;
import com.google.common.collect.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName DictTypeServiceApiImpl
 * @Description
 * @Author huangjinhui
 * @Date 2022/4/21 10:59
 * @Version 1.0
 */
@Service
public class DictTypeServiceApiImpl implements DictTypeServiceApi {

    @Autowired
    private ISysDictTypeService iSysDictTypeService;

    @Override
    public Table<String, String, String> getDictTable(String... keys) {
        return iSysDictTypeService.getDictTable(keys);
    }

    @Override
    public String getDictRemark(String dictType, String dictValue) {
        String dictRemark = DictUtils.getDictRemark(dictType, dictValue);
        if (StringUtils.isNotEmpty(dictRemark)) {
            return dictRemark;
        }
        iSysDictTypeService.selectDictDataByTypeWithCache(dictType);
        // 再查一次
        return DictUtils.getDictRemark(dictType, dictValue);
    }


}
