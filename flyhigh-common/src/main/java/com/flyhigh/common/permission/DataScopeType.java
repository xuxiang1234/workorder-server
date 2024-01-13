package com.flyhigh.common.permission;

import com.flyhigh.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataScopeType {

    /**
     * 默认无权限
     */
    NONE("", "", "1 = 0"),

    /**
     * 全部数据权限
     */
    ALL("1", "", ""),

    /**
     * 自定数据权限
     */
    CUSTOM("2", " #{#deptName} IN ( #{@sdss.getRoleCustom( #user.roleId )} ) ", ""),

    /**
     * 部门数据权限
     */
    DEPT("3", " #{#deptName} = #{#user.deptId} ", ""),

    /**
     * 部门及以下数据权限
     */
    DEPT_AND_CHILD("4", " #{#deptName} IN ( #{@sdss.getDeptAndChild( #user.deptId )} )", ""),

    /**
     * 仅本人数据权限
     */
    SELF("5", " #{#userName} = #{#user.userId} ", " 1 = 0 "),

    /**
     * 相关人（例如，项目中的三级网格员，会议中的参会人员等等）
     * 目的：查询出如果是其他人员类型的筛选条件
     */
    RELATEDUSERNAME("7", " #{#relatedUserName} = #{#user.userId} ", ""),

    /**
     * 查询自己所有的部门数据权限，或者所属自己的数据
     */
    CUSTOM_DEPT_OR_SELF("6", " #{#deptName} IN ( #{@sdss.getRoleCustom( #user.roleId )} ) or #{#userName} = #{#user.userId} ", ""),


    CUSTOM_SQL("8", " #{#sql}", "");

    private final String code;

    /**
     * 语法 采用 spel 模板表达式
     */
    private final String sqlTemplate;

    /**
     * 不满足 sqlTemplate 则填充
     */
    private final String elseSql;

    public static DataScopeType findCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (DataScopeType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
