package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrainingSubTypeEnum {

    /**
     * 培训附件
     */
    TRAINING_DATA((short) 1),
    /**
     * 培训纪要
     */
    TRAINING_MINUTES((short) 2),
    /**
     * 培训图片
     */
    TRAINING_IMG((short) 3),

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
     * 应急演练
     */
    NOTICE_CONTINGENCY_MANAGEMENT((short) 8);

    private short code;
}
