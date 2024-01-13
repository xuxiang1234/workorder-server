package com.flyhigh.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal工具类
 *
 * @author Mr.Lai
 * @date 2023/4/14
 */
public class BigDecimalUtil {

    /**
     * 默认最小值为0
     *
     * @param bigDecimal
     * @return
     */
    public static BigDecimal minZero(BigDecimal bigDecimal) {
        if(null == bigDecimal || bigDecimal.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return bigDecimal;
    }

    /**
     * 计算百分比
     *
     * @param total
     * @param number
     * @return
     */
    public static BigDecimal calculatePercentage(Long total, Long number) {
        if(0 == total || 0 == number) {
            return BigDecimal.ZERO;
        }
        BigDecimal divide = BigDecimal.valueOf(number).divide(BigDecimal.valueOf(total), 4, RoundingMode.HALF_UP);
        return divide.multiply(BigDecimal.valueOf(100));
    }

    /**
     * 计算百分比
     *
     * @param total
     * @param number
     * @return
     */
    public static BigDecimal calculatePercentage(BigDecimal total, BigDecimal number) {
        if(total.compareTo(BigDecimal.ZERO) == 0 || number.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal divide = number.divide(total, 4, RoundingMode.HALF_UP);
        return divide.multiply(BigDecimal.valueOf(100));
    }

}
