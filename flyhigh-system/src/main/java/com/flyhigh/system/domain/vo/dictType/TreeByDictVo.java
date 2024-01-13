package com.flyhigh.system.domain.vo.dictType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

@ApiModel("字典数据树形结构")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeByDictVo {

    @ApiModelProperty("字典id")
    private Long dictId;

    /*标签*/
    @ApiModelProperty("标签")
    private String label;

    /*值*/
    @ApiModelProperty("值")
    private String value;

    @ApiModelProperty("层级,第一级=1，每级递增")
    private Integer level;

    @ApiModelProperty("样式")
    private String cssClass;

    /*子节点*/
    @ApiModelProperty("子节点")
    private List<TreeByDictVo> children = Lists.newArrayList();


}
