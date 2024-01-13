package com.flyhigh.form.service;

import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.api.vo.formWarehouse.ScoreFormPageReq;
import com.flyhigh.api.vo.formWarehouse.ScoreFormVO;

/**
 * @author Mr.Lai
 * @date 2023/5/5
 */
public interface IScoreFormService {

    /**
     * 查询评分表单列表
     *
     * @param pageReq
     * @return
     */
    TableDataInfo<ScoreFormVO> queryPageList(ScoreFormPageReq pageReq);

    /**
     * 激活评分表单
     *
     * @param id 表单ID
     * @return 激活结果
     */
    Boolean activateForm(Long id);

}
