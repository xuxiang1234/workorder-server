package com.flyhigh.api.dto.smsPush;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 用户个推别名视图对象 biz_push_user_alias
 *
 * @author flyhigh
 * @date 2022-03-31
 */
@Data
@ApiModel("用户绑定视图")
public class BizPushBindReq {

    private static final long serialVersionUID = 1L;

    /**
     * 用户cid
     */
    @ApiModelProperty("用户cid")
    private String cid;

}
