package com.flyhigh.system.domain.vo.sysUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Token用户信息
 *
 * @author flyhigh
 */
@Api(value = "Token用户信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenVo {

    /**
     * token参数
     */
    @ApiModelProperty(value = "token参数")
    private String token;

    /**
     * 是否绑定商户-true：已绑定 false：未绑定
     */
    @ApiModelProperty(value = "是否绑定商户-true：已绑定 false：未绑定")
    private Boolean isBind;

}
