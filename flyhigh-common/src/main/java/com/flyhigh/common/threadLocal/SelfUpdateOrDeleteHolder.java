package com.flyhigh.common.threadLocal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 只允许自己更新或删除，数据持有者
 */
public class SelfUpdateOrDeleteHolder {
    private static final InheritableThreadLocal<Info> HOLDER = new InheritableThreadLocal<>();

    public static void put(Info info) {
        HOLDER.set(info);
    }

    public static Info get() {
        return HOLDER.get();
    }

    public static void remove() {
        HOLDER.remove();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Info {
        /**
         * 数据id
         */
        private Object dataId;

        /**
         * 用户id
         */
        private Long userId;

        /**
         * entity数组
         */
        private Class[] entities;
    }
}
