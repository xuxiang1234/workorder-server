package com.flyhigh.system.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 上传文件视图对象VO
 *
 * @author flyhigh
 * @date 2022-12-29
 */
@ApiModel("上传文件视图对象VO")
@Data
@NoArgsConstructor
public class UploadFileVO {

    private static final long serialVersionUID = 1L;

    /**
     * 文件ID
     */
    @ApiModelProperty("文件ID")
    private Long id;

    /**
     * URL地址
     */
    @ApiModelProperty("URL地址")
    private String url;

    /**
     * URL短链接地址
     */
    @ApiModelProperty("URL短链接地址")
    private String shortUrl;

    /**
     * 文件原名
     */
    @ApiModelProperty("文件原名")
    private String originalName;

    public UploadFileVO(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public UploadFileVO(Long id, String shortUrl) {
        this.id = id;
        this.shortUrl = shortUrl;
    }

    public UploadFileVO(Long id, String shortUrl, String originalName) {
        this.id = id;
        this.shortUrl = shortUrl;
        this.originalName = originalName;
    }

}
