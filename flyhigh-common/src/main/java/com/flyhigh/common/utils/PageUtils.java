package com.flyhigh.common.utils;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.function.Function;

public final class PageUtils {

    private PageUtils() {

    }

    public static <T, C> IPage<C> copyPage(IPage<T> pageData, Class<C> voClass) {
        IPage<C> voPage = new Page<>(pageData.getCurrent(), pageData.getSize(), pageData.getTotal());
        if (CollUtil.isEmpty(pageData.getRecords())) {
            return voPage;
        }
        voPage.setRecords(BeanCopyUtils.copyList(pageData.getRecords(), voClass));
        return voPage;
    }

    public static <T, C> IPage<C> copyPage(IPage<T> pageData, Function<List<T>, List<C>> function) {
        IPage<C> voPage = new Page<>(pageData.getCurrent(), pageData.getSize(), pageData.getTotal());
        if (CollUtil.isEmpty(pageData.getRecords())) {
            return voPage;
        }
        voPage.setRecords(function.apply(pageData.getRecords()));
        return voPage;
    }
}
