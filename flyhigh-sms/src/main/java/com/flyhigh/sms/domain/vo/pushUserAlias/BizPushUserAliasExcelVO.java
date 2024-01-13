package com.flyhigh.sms.domain.vo.pushUserAlias;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 用户个推别名 Excel VO
 *
 * @author flyhigh
 */
@Data
public class BizPushUserAliasExcelVO {

    @ExcelProperty("${column.columnComment}")
    private Long userId;

    @ExcelProperty("用户别名")
    private String userAlias;

    @ExcelProperty("备注")
    private String remark;

}
