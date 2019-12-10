package william.eshop.param;

import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description 提交订单请求参数
 */
@Data
@ApiModel(value = "CommitOrderParam", description = "提交订单请求参数")
public class CommitOrderParam {
    @ApiModelProperty(value = "买家用户id", name = "userId", required = true)
    private String userId;

    @ApiModelProperty(value = "收货人姓名", name = "receiverName", required = true)
    private String receiverName;

    @ApiModelProperty(value = "收货人手机号", name = "receiverMobile", required = true)
    private String receiverMobile;

    @ApiModelProperty(value = "收货地址", name = "receiverAddress", required = true)
    private String receiverAddress;

    @ApiModelProperty(value = "商品规格列表", name = "itemSpecIds", required = true)
    private List<ItemSpecParam> itemSpecs;

    @ApiModelProperty(value = "收货地址", name = "address", required = true)
    private String address;

    @ApiModelProperty(value = "支付方式", name = "payMethod", required = true)
    private int payMethod;

    @ApiModelProperty(value = "买家留言", name = "leftMsg")
    private String leftMsg;

    public boolean isIllegal() {
        if (StringUtils.isEmpty(userId)) {
            return true;
        }
        if (CollectionUtils.isEmpty(itemSpecs)) {
            return true;
        }
        return StringUtils.isEmpty(address);
    }
}
