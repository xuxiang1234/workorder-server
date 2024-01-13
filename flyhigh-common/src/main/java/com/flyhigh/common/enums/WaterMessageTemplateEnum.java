package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName MessageTemplateEnum
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/15 10:18
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum WaterMessageTemplateEnum {

    SAFE_CHECK_CREATE("创建安全检查", "safe_check_create", "1", "1"),

    SAFE_MEETING_CREATE("创建安全会议", "safe_meeting_create", "2", "1,2"),

    SAFE_TRAINING_CREATE("创建安全培训", "safe_training_create", "3", "1,2"),

    CONTINGENCY_MANAGEMENT_CREATE("创建应急演练", "contingency_management_create", "4", "1,2"),

    SEND_DOCUMENT_CREATE("创建发文", "send_document", "5", "3");

    /**
     * 模板名称
     */
    private String name;

    /**
     * 字典value
     */
    private String dictValue;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 业务分类 类型 1 日常工作 2 教育基地 3 发文
     */
    private String busType;

    /**
     * 根据数字类型换取中文类型
     *
     * @param type
     * @return
     */
    public static String getMessageType(String type) {
        return Stream.of(WaterMessageTemplateEnum.values()).filter(template -> template.type.equals(type)).map(item -> item.getName().split("-")[0]).findFirst().orElse(null);
    }

    /**
     * 前端首页查询  1 日常工作 2 教育基地 3 发文
     *
     * @param busType
     * @return
     */
    public static List<Integer> getMessages(String busType) {
        return Stream.of(WaterMessageTemplateEnum.values()).filter(template -> template.busType.contains(busType)).map(item -> Integer.valueOf(item.getType())).collect(Collectors.toList());

    }
}
