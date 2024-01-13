package com.flyhigh.api.service;

import com.google.common.collect.Table;

/**
 * @ClassName DictTypeServiceApi
 * @Description
 * @Author huangjinhui
 * @Date 2022/4/21 10:58
 * @Version 1.0
 */
public interface DictTypeServiceApi {

    /**
     * 多个key获取字典
     *
     * @param keys
     * @return
     */
    Table<String, String, String> getDictTable(String... keys);

    /**
     * 通过字典类型和value获取remark
     *
     * @param dictType
     * @param dictValue
     * @return
     */
    String getDictRemark(String dictType, String dictValue);

}
