package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * 字典类型
 *
 * @author flyhigh
 */
@AllArgsConstructor
@Getter
public enum DictTypeEnum {

    /**
     * 资产单元-房屋属性
     */
    ASSETS_UNIT_ATTRIBUTE("assets_unit_attribute_", "房屋属性"),

    /**
     * 资产单元-设施清单列表
     */
    ASSETS_FACILITIES_LIST("assets_facilities_list", "设施清单列表"),

    /**
     * 售卖信息-售卖类型
     */
    SALE_TYPE("sale_type", "售卖类型"),

    /**
     * 售卖信息-付款方式
     */
    SALE_PAY_AMOUNT_TYPE("sale_pay_amount_type", "付款方式"),

    /**
     * 系统用户岗位名称
     */
    SYS_USER_POST("sys_user_post", "岗位名称"),

    /**
     * 安全检查系统登录
     */
    SAFETY_AUTH_LOGIN("safety_auth_login", "安全检查系统登录"),

    /**
     * 消息模板
     */
    MESSAGE_TEMPLATE("message_template", "消息模板"),

    /**
     * 微信模板消息配置
     */
    WX_MESSAGE_TEMPLATE("wx_message_template", "微信模板消息配置"),

    /**
     * 租赁资料文件类型
     */
    LEASING_DOCUMENT_TYPE("leasing_document_type", "租赁资料文件类型"),

    /**
     * 设备类型
     */
    OPERATIONAL_DEVICE_TYPE("operational_device_type", "设备类型"),

    /**
     * 工单类型
     */
    WORK_ORDER_TYPE("work_order_type", "工单类型"),

    /**
     * 运维保养周期
     */
    OPERATIONAL_SERVICE_INTERVAL("operational_service_interval", "运维保养周期"),

    ;

    private String type;

    private String desc;

    /**
     * 根据分类获取详细信息
     *
     * @param type
     * @return
     */
    public static String getDescByType(String type) {
        return Stream.of(DictTypeEnum.values())
                .filter(o -> o.getType().equals(type))
                .map(DictTypeEnum::getDesc).findFirst()
                .orElse(null);
    }

}