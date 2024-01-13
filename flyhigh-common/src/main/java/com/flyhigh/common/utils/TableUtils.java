package com.flyhigh.common.utils;

import com.flyhigh.common.constant.HttpStatus;
import com.flyhigh.common.core.page.TableDataInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class TableUtils {

    public static <T> TableDataInfo<T> getDataTable(List<T> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new Long(new PageInfo(list).getTotal()).intValue());
        return rspData;
    }
}
