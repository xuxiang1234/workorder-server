package com.flyhigh.api.vo.home;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.compress.utils.Lists;

import java.math.BigDecimal;
import java.util.List;

/**
 * 租赁单元总缴费金额
 *
 * @author Mr.Lai
 * @date 2023/4/11
 */
@ApiModel("租赁单元总缴费金额 VO")
@Data
@ToString(callSuper = true)
public class HomeLeaseAssetsUnitVO {

    /**
     * 预计总缴费金额
     */
    @ApiModelProperty(value = "预计总缴费金额")
    private List<BigDecimal> expectPaymentAmountTotal;

    /**
     * 实际总缴费金额
     */
    @ApiModelProperty(value = "实际总缴费金额")
    private List<BigDecimal> realityPaymentAmountTotal;

    /**
     * 名称列表
     */
    @ApiModelProperty(value = "名称列表")
    private List<String> label;

    public List<BigDecimal> getExpectPaymentAmountTotal() {
        return CollectionUtils.isNotEmpty(expectPaymentAmountTotal) ? expectPaymentAmountTotal : Lists.newArrayList();
    }

    public List<BigDecimal> getRealityPaymentAmountTotal() {
        return CollectionUtils.isNotEmpty(realityPaymentAmountTotal) ? realityPaymentAmountTotal : Lists.newArrayList();
    }

    public List<String> getLabel() {
        return CollectionUtils.isNotEmpty(label) ? label : Lists.newArrayList();
    }

}
