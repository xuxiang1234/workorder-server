package com.flyhigh.common.utils.premission;

import com.flyhigh.common.utils.StringUtils;

/**
 * @ClassName DataScopeSqlHelper
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/30 17:54
 * @Version 1.0
 */
public class DataScopeSqlHelper {

    /**
     * 用于构建 biz_participation_staff表作为参与人的sql拼接
     * OR id IN (SELECT participation_id FROM biz_participation_staff WHERE participation_type = 8 AND user_id = 102))
     *
     * @param participationType
     * @param userId
     */
    public static void buildParticipationSql(short participationType, Long userId) {
        String sql = " id in ( select participation_id from biz_participation_staff where participation_type = {} and user_id = {} )";
        String format = StringUtils.format(sql, participationType, userId);
        DataPermissionHelper.setVariable("sql", format);
    }
}
