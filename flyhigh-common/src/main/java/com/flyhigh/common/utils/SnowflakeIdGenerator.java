package com.flyhigh.common.utils;

/**
 * 雪花算法id生成器
 */
public class SnowflakeIdGenerator {

    private static volatile SnowflakeIdWorker snowflakeIdWorker;

    /**
     * generate UUID using snowflake algorithm
     *
     * @return UUID
     */
    public static long generateID() {
        if (snowflakeIdWorker == null) {
            synchronized (SnowflakeIdGenerator.class) {
                if (snowflakeIdWorker == null) {
                    init(null);
                }
            }
        }
        return snowflakeIdWorker.nextId();
    }

    /**
     * init IdWorker
     *
     * @param serverNode the server node id, consider as machine id in snowflake
     */
    private static void init(Long serverNode) {
        snowflakeIdWorker = new SnowflakeIdWorker(0, 0);
    }
}
