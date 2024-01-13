package com.flyhigh.sms.domain.vo.pushUserCid;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 用户个推cid关系绑定 Excel VO
 *
 * @author flyhigh
 */
@Data
public class BizPushUserCidExcelVO {

    @ExcelProperty("${column.columnComment}")
    private Long userId;

    @ExcelProperty("个推cid")
    private String cid;

    @ExcelProperty("用户别名")
    private String userAlias;

    @ExcelProperty("备注")
    private String remark;

}
