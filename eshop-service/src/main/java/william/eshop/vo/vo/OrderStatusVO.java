package william.eshop.vo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2020-05-08
 * @Description 订单状态VO
 */
@Data
@ApiModel(value = "OrderStatusVO", description = "订单状态VO")
public class OrderStatusVO {
    @ApiModelProperty(value = "订单id", name = "orderId", required = true)
    private String orderId;

    @ApiModelProperty(value = "订单状态", name = "status", required = true)
    private int status;

    @ApiModelProperty(value = "状态描述", name = "desc", required = true)
    private String desc;
}
