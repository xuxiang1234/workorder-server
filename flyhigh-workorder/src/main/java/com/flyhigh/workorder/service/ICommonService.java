package com.flyhigh.workorder.service;

import java.util.List;

/**
 * 模板Service接口
 *
 * @author flyhigh
 * @date 2022-05-15
 */
public interface ICommonService {


    /**
     * 查询字典列表
     *
     * @param dictType 字典主键
     * @return 字典集合
     */
    List<String> queryDictList(String dictType);
}
