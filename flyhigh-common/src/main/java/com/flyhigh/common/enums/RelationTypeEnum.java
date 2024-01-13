package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * biz_participation_staff表 参与类型枚举
 *
 * @author flyhigh
 */
@AllArgsConstructor
@Getter
public enum RelationTypeEnum {

    /**
     * 培训
     */
    TRAINING((short) 1),

    /**
     * 会议
     */
    MEETING((short) 2),

    /**
     * 发文
     */
    SEND_DOC((short) 3),

    /**
     * 安全持证
     */
    CERTIFICATE((short) 4),

    /**
     * 责任承诺书
     */
    RESPONSIBILITY((short) 5),

    /**
     * 标准化制度
     */
    STANDARDIZATION_SYSTEM((short) 6),

    /**
     * 安全检查
     */
    SAFE_CHECK((short) 7),


    /**
     * 应急演练
     */
    NOTICE_CONTINGENCY_MANAGEMENT((short) 8);

    private short code;

}