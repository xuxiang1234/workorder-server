package com.flyhigh.common.exception;

/**
 * System 错误码枚举类
 * <p>
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {

    ErrorCode SYSTEM_ERROR = new ErrorCode(500, "系统异常");
    ErrorCode DATA_ERROR = new ErrorCode(501, "数据异常");

    // ========== AUTH 模块 1002000000 ==========
    ErrorCode AUTH_LOGIN_BAD_CREDENTIALS = new ErrorCode(1002000000, "登录失败，账号密码不正确");
    ErrorCode AUTH_LOGIN_USER_DISABLED = new ErrorCode(1002000001, "登录失败，账号被禁用");
    ErrorCode AUTH_LOGIN_FAIL_UNKNOWN = new ErrorCode(1002000002, "登录失败"); // 登录失败的兜底，未知原因
    ErrorCode AUTH_LOGIN_CAPTCHA_NOT_FOUND = new ErrorCode(1002000003, "验证码不存在");
    ErrorCode AUTH_LOGIN_CAPTCHA_CODE_ERROR = new ErrorCode(1002000004, "验证码不正确");
    ErrorCode AUTH_THIRD_LOGIN_NOT_BIND = new ErrorCode(1002000005, "未绑定账号，需要进行绑定");
    ErrorCode AUTH_TOKEN_EXPIRED = new ErrorCode(1002000006, "Token 已经过期");

    // ========== 菜单模块 1002002000 ==========
    ErrorCode MENU_NAME_DUPLICATE = new ErrorCode(1002002000, "已经存在该名字的菜单");
    ErrorCode MENU_PARENT_NOT_EXISTS = new ErrorCode(1002002001, "父菜单不存在");
    ErrorCode MENU_PARENT_ERROR = new ErrorCode(1002002002, "不能设置自己为父菜单");
    ErrorCode MENU_NOT_EXISTS = new ErrorCode(1002002003, "菜单不存在");
    ErrorCode MENU_EXISTS_CHILDREN = new ErrorCode(1002002004, "存在子菜单，无法删除");
    ErrorCode MENU_PARENT_NOT_DIR_OR_MENU = new ErrorCode(1002002005, "父菜单的类型必须是目录或者菜单");

    // ========== 角色模块 1002003000 ==========
    ErrorCode ROLE_NOT_EXISTS = new ErrorCode(1002003000, "角色不存在");
    ErrorCode ROLE_NAME_DUPLICATE = new ErrorCode(1002003001, "已经存在名为【{}】的角色");
    ErrorCode ROLE_CODE_DUPLICATE = new ErrorCode(1002003002, "已经存在编码为【{}】的角色");
    ErrorCode ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE = new ErrorCode(1002003004, "不能操作类型为系统内置的角色");
    ErrorCode ROLE_IS_DISABLE = new ErrorCode(1002003004, "名字为【{}】的角色已被禁用");

    // ========== 用户模块 1002004000 ==========
    ErrorCode USER_USERNAME_EXISTS = new ErrorCode(1002004000, "用户账号已经存在");
    ErrorCode USER_MOBILE_EXISTS = new ErrorCode(1002004001, "手机号已经存在");
    ErrorCode USER_EMAIL_EXISTS = new ErrorCode(1002004002, "邮箱已经存在");
    ErrorCode USER_NOT_EXISTS = new ErrorCode(1002004003, "用户不存在");
    ErrorCode USER_IMPORT_LIST_IS_EMPTY = new ErrorCode(1002004004, "导入用户数据不能为空！");
    ErrorCode USER_PASSWORD_FAILED = new ErrorCode(1002004005, "用户密码校验失败");
    ErrorCode USER_IS_DISABLE = new ErrorCode(1002003004, "名字为【{}】的用户已被禁用");
    ErrorCode USER_IMPORT_TEMPLATE_ERROR = new ErrorCode(1002004006, "导入模板错误！");

    // ========== 岗位模块 1002005000 ==========
    ErrorCode POST_NOT_FOUND = new ErrorCode(1002005001, "当前岗位不存在");
    ErrorCode POST_NOT_ENABLE = new ErrorCode(1002005002, "岗位({}) 不处于开启状态，不允许选择");
    ErrorCode POST_NAME_DUPLICATE = new ErrorCode(1002005001, "已经存在该名字的岗位");
    ErrorCode POST_CODE_DUPLICATE = new ErrorCode(1002005001, "已经存在该标识的岗位");

    // ========== 审批模块 1002006000 ==========
    ErrorCode AUDIT_TEMPLATE_ROLE_REPEATER = new ErrorCode(1002005001, "角色已被使用");

    // ========== 计划管理模块 1002007000 ==========
    ErrorCode PLAN_YEAR_NOT_SUPPORT = new ErrorCode(1002007001, "已过时间不允许操作计划");
    ErrorCode PLAN_YEAR_HAS_EXIST = new ErrorCode(1002007002, "本年度已添加，请勿重复添加");
    ErrorCode OPERATE_NOT_ALLOWED = new ErrorCode(1002007003, "请联系管理员赋予角色权限");
    ErrorCode PLAN_DETAIL_EXISTS_NOT_ALLOWED = new ErrorCode(1002007004, "计划子列存在数据，无法删除");
    ErrorCode CREATE_TYPE_ERROR = new ErrorCode(1002007005, "请选择正确的创建类型");
    ErrorCode CREATE_TYPE_VALUE_ERROR = new ErrorCode(1002007006, "暂无可操作的月份计划，请检查分类或者填写日期");
    ErrorCode TOTAL_PLAN_NUMBER_NOT_AVERAGE_ENOUGH = new ErrorCode(1002007007, "请确保每个与至少一次");

    // ========== 会议管理模块 1002008000 ==========
    ErrorCode MEETING_FINISH = new ErrorCode(1002008001, "会议已经结束");

    // ========== 消息模块 ==========
    ErrorCode MESSAGE_TEMPLATE_NOT_EXISTS = new ErrorCode(1002009001, "消息模板类型不粗在");
    ErrorCode MESSAGE_TEMPLATE_PARSER_ERROR = new ErrorCode(1002009002, "参数模板解析失败，请检查参数");
    ErrorCode MESSAGE_NOT_USERS = new ErrorCode(1002009003, "请选择接收消息的人员");
    ErrorCode MESSAGE_NOT_business_idS = new ErrorCode(1002009004, "业务id不能为空");
    // push
    ErrorCode MESSAGE_BIND_ERROR = new ErrorCode(1002009005, "推送消息服务绑定失败，请联系管理员");

    // ========== 审批模块 ==========
    ErrorCode FLOWINFO_INCOMPLETE_APPROVAL = new ErrorCode(1003001001, "存在待审批的流程");
    ErrorCode FLOWINFO_NODE_NOT_EXISTS = new ErrorCode(1003001002, "审批节点不存在");
    ErrorCode FLOWINFO_NODE_NOT_CURRENTUSER = new ErrorCode(1003001003, "当前审核人和节点审核人不符合");
    ErrorCode FLOWINFO_NODE_NOT_PENDING_APPROVAL = new ErrorCode(1003001004, "当前节点不满足审核条件");


    // ========== 首页 ==========
    ErrorCode SHORTCUT_MENT_EXISTS = new ErrorCode(1003002001, "快捷菜单已存在");

    // ============ 应急预案=========
    ErrorCode EMERGENCYDRILL_FINISHD = new ErrorCode(1003003001, "应急预案已经完成");

    // ============ 安全检查=========
    ErrorCode SAFE_CHECK_NO_TROUBLE = new ErrorCode(1003004001, "隐患为空，无法审批");

    ErrorCode SAFE_CHECK_NO_SUBMIT_TROUBLE = new ErrorCode(1003004002, "尚未提交整改无法审批");

    // ==========工单模块 ==========
    ErrorCode PROCESS_USER_ERROR = new ErrorCode(1005001001, "流程中不允许指定发起人");
    ErrorCode GROUP_USER_NOT_EXISTS = new ErrorCode(1005001002, "组用户不存在");
    ErrorCode TEMPLATE_NAME_EXISTS = new ErrorCode(1005001003, "模板名称已存在");
    ErrorCode WORK_ORDER_NOT_START = new ErrorCode(1005001004, "工单未开始");

    // ==========微信模块 ==========
    ErrorCode WX_ERROR_CODE = new ErrorCode(1006001001, "code不合法或已失效");
    ErrorCode WX_AUTH_EXPIRED = new ErrorCode(1006001002, "登录状态已经过期");
    ErrorCode WX_UNBIND_TENANT = new ErrorCode(1006002001, "未绑定商户");

    // ========== 运维模块 ==========
    ErrorCode WORK_ORDER_ALREADY_STARTED = new ErrorCode(1006002001, "工单已开启");
    ErrorCode WORK_ORDER_PROGRESS_NO_DELETE = new ErrorCode(1006002002, "工单无法删除");
    ErrorCode WAREHOUSE_MAXVALUE_OVERFLOW = new ErrorCode(1006002001, "库存超过最大值"+Integer.MAX_VALUE);
    ErrorCode MEDICINE_MAXVALUE_OVERFLOW = new ErrorCode(1006002001, "库存不足");
}
