package com.flyhigh.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * 数据脱敏工具类
 * 脱敏数据列表：姓名、身份证号、手机号、地址、电子邮箱
 * 脱敏规则：
 * 姓名：只显示第一个汉字，如：李**
 * 身份证：显示最后4位，如：*************1234
 * 手机号码：前3位 后4位，如：157****5591
 * 地址：只显示到地区，不显示地址，如：江苏省苏州市吴中区***
 * 电子邮箱：只显示第一个字母，如：d**@163.com
 *
 * @author Mr.mine
 * @date 2022/4/10
 */
public class DesensitizedUtil {

    private static final String DEFAULT_STR = "***";

    /**
     * 姓名
     * 例：李某某->李**
     *
     * @param str 需要脱敏的数据
     * @return 脱敏后的数据
     */
    public static String name(String str) {
        return Optional.ofNullable(str)
                .map(value -> StringUtils.left(value, 1))
                // 截取第一位
                .map(value -> StringUtils.rightPad(value, StringUtils.length(str), "*"))
                // 右侧补位
                .orElse(DEFAULT_STR);
    }

    /**
     * 身份证
     *
     * @param str 需要脱敏的数据
     * @return 脱敏后的数据
     */
    public static String idCard(String str) {
        return Optional.ofNullable(str)
                .map(value -> StringUtils.right(value, 4))
                // 截取最后四位
                .map(value -> StringUtils.leftPad(value, StringUtils.length(str), "*"))
                // 左侧补位
                .orElse(DEFAULT_STR);
    }

    /**
     * 手机号
     *
     * @param str 需要脱敏的数据
     * @return 脱敏后的数据
     */
    public static String phone(String str) {
        return Optional.ofNullable(str)
                .map(value -> StringUtils.left(value, 3))
                // 截取前3位
                .map(value -> value.concat(
                        StringUtils.removeStart(
                                StringUtils.leftPad(StringUtils.right(str, 4), StringUtils.length(str), "*"),
                                "***")
                ))
                // 取后四位，左侧补位，删除前三位***后拼接
                .orElse(DEFAULT_STR);
    }

    /**
     * 地址
     *
     * @param str    需要脱敏的数据
     * @param length 脱敏的数据长度
     * @return
     */
    public static String address(String str, int length) {
        int strLength = StringUtils.length(str);
        // 长度小与0时对半显示
        int limit = strLength - length < 0 || length <= 0 ? strLength - Math.round(strLength / 2) : strLength - length;
        return Optional.ofNullable(str)
                .map(value -> StringUtils.left(value, limit))
                // 需要显示的内容
                .map(value -> StringUtils.rightPad(value, StringUtils.length(str), "*"))
                // 右侧补齐
                .orElse(DEFAULT_STR);
    }

    /**
     * 邮箱
     *
     * @param str 需要脱敏的数据
     * @return 脱敏后的数据
     */
    public static String email(String str) {
        int index = StringUtils.indexOf(str, "@");
        return Optional.ofNullable(str)
                .filter(value -> index > 1)
                .map(value -> StringUtils.rightPad(StringUtils.left(str, 1), index, "*"))
                // 截取前1位到@截止
                .map(value -> value.concat(StringUtils.mid(str, index, StringUtils.length(str))))
                // 拼接截取@xxx
                .orElse(DEFAULT_STR);
    }

    public static void main(String[] args) {
        // 姓名
        String text = "李某某";
        String name = name(text);
        System.out.println(name);
        // 身份证
        String idCard = "10000123123123123456";
        String s = idCard(idCard);
        System.out.println(s);
        // 手机号
        String phone = "15779775592";
        String phone1 = phone(phone);
        System.out.println(phone1);
        // 邮箱
        String email = "t@xxx.com";
        String email1 = email(email);
        System.out.println(email1);
        // 地址
        String address = "江苏省苏州市工业园区斜塘街道";
        String address1 = address(address, 5);
        System.out.println(address1);
    }
}
