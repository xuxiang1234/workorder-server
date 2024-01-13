package com.flyhigh.system.service.impl;

import com.flyhigh.system.service.SysDataScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service层通用数据处理
 *
 * @author Mr.Lai
 * @date 2023/3/24
 */
@Service
public class BaseServiceImpl {

    @Autowired
    protected SysDataScopeService sysDataScopeService;

}
