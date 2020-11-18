package william.eshop.param.order;

import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import william.eshop.param.item.ItemSpecParam;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description 提交订单请求参数
 */
@Data
@ApiModel(value = "CommitOrderParam", description = "提交订单请求参数")
public class CommitOrderParam {
    @ApiModelProperty(value = "收货地址id", name = "addressId", required = true)
    private String addressId;

    @ApiModelProperty(value = "商品规格列表", name = "itemSpecIds", required = true)
    private List<ItemSpecParam> itemSpecs;

    @ApiModelProperty(value = "支付方式", name = "payMethod", required = true)
    private int payMethod;

    @ApiModelProperty(value = "买家留言", name = "leftMsg")
    private String leftMsg;

    public boolean isIllegal() {
        if (StringUtils.isEmpty(addressId)) {
            return true;
        }
        if (CollectionUtils.isEmpty(itemSpecs)) {
            return true;
        }
        return itemSpecs.stream().anyMatch(i -> i.getCount() <= 0);
    }
}
