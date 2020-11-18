package william.eshop.param.order;

import org.springframework.util.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2019-12-12
 * @Description 微信支付通知参数
 */
@Data
@ApiModel(value = "WechatPaidNotifyParam", description = "微信支付通知参数")
public class WechatPaidNotifyParam {
    @ApiModelProperty(value = "商户订单id", name = "orderId", required = true)
    private String orderId;

    public boolean isIllegal() {
        return (StringUtils.isEmpty(orderId));
    }
}
