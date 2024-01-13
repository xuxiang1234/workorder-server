package com.flyhigh.common.utils;

import java.util.regex.Pattern;

/**
 * 正则验证
 *
 * @author Mr.lai
 */
public class PatternUtil {

    /**
     * 正则表达式：小数
     */
    public static final String REGEX_DECIMAL = "^[0-9]+(\\.?[0-9]+)?$";

    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^\\w{2,20}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^(1[3456789]\\d{9}$)|(0\\d{2,3})-?(\\d{7,8})";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 正则表达式：银行卡号
     */
    public static final String REGEX_BANK_CARD_NUMBER = "^[0-9]{9,25}$";

    /**
     * 正则表达式：税号
     */
    public static final String REGEX_DUTY_PARAGRAPH = "^[A-Z0-9]{15}$|^[A-Z0-9]{17}$|^[A-Z0-9]{18}$|^[A-Z0-9]{20}$";


    /**
     * 校验小数
     *
     * @param decimal
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isDecimal(String decimal) {
        return Pattern.matches(REGEX_DECIMAL, decimal);
    }

    /**
     * 校验用户名
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验密码
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验URL
     *
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * 校验IP地址
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIP(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

    /**
     * 银行卡号
     *
     * @param bankCardNumber
     * @return
     */
    public static boolean isBankCardNumber(String bankCardNumber) {
        return Pattern.matches(REGEX_BANK_CARD_NUMBER, bankCardNumber);
    }

    /**
     * 校验税号
     *
     * @param dutyParagraph
     * @return
     */
    public static boolean isDutyParagraph(String dutyParagraph) {
        return Pattern.matches(REGEX_DUTY_PARAGRAPH, dutyParagraph);
    }

}