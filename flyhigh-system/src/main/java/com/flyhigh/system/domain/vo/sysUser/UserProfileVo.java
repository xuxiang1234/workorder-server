package com.flyhigh.system.domain.vo.sysUser;

import com.flyhigh.common.core.domain.entity.SysDept;
import com.flyhigh.common.core.domain.entity.SysUser;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * 用户个人信息色vo
 *
 * @author Mr.Lai
 */
@Data
@ApiModel("用户个人信息色vo")
public class UserProfileVo {

    /**
     * 用户所属角色组
     */
    private String roleGroup;

    /**
     * 用户所属岗位组
     */
    private String postGroup;

    /**
     * 所有dept
     */
    private List<SysDept> company;

    /**
     * 用户信息
     */
    private SysUser user;

    /**
     * 是否调度员
     */
    private Boolean whetherDispatcherUser;
}
