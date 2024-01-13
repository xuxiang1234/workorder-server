package com.flyhigh.common.constant;

/**
 * 账单常量
 *
 * @author Mr.Lai
 */
public interface BillOrderConstants {

    /**
     * 租赁租金账单名称
     * yearMonth：年月
     */
    String LEASE_RENT_NAME = "${yearMonth}的租金账单";

    /**
     * 租赁租金+押金账单名称
     * yearMonth：年月
     */
    String LEASE_RENT_DEPOSIT_NAME = "${yearMonth}的租金+押金账单";

    /**
     * 租赁水费账单名称
     * yearMonth：年月
     */
    String LEASE_WATER_METER_ELECTRIC_NAME = "${yearMonth}的水费账单";

    /**
     * 租赁电费账单名称
     * yearMonth：年月
     */
    String LEASE_ELECTRIC_METER_ELECTRIC_NAME = "${yearMonth}的电费账单";

    /**
     * 租赁物业费账单名称
     * yearMonth：年月
     */
    String LEASE_PROPERTY_NAME = "${yearMonth}的物业费账单";

    /**
     * 退租账单名称
     * yearMonth：年月
     */
    String TERMINATE_NAME = "${yearMonth}的退租账单";

    /**
     * 提前15天生成账单
     */
    Integer GENERATE_BILL_ORDER_DAY = -15;

}
