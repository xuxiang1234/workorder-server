package com.flyhigh.common.utils;

public final class MaskUtils {
    public MaskUtils() {

    }

    private static final String SP = "****";

    public static String maskByMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return StringUtils.EMPTY;
        }
        if (mobile.length() == 11) {
            return mobile.substring(0, 3) + SP + mobile.substring(7, mobile.length());
        } else if (mobile.length() > 4) {
            return mobile.substring(4) + SP;
        } else {
            return SP;
        }
    }

    public static String maskByIp(String ip) {
        if (StringUtils.isBlank(ip)) {
            return StringUtils.EMPTY;
        }
        String[] ary = ip.split(".");
        if (ary.length == 4) {
            return ary[0] + ".*.*." + ary[3];
        }
        return ip;
    }
}
