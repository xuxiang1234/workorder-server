package com.flyhigh.system.domain.vo.sysUser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户头像vo
 *
 * @author Mr.Lai
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户头像vo")
public class UserAvatarVo {

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String imgUrl;
}
