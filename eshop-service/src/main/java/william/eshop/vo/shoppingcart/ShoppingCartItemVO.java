package william.eshop.vo.shoppingcart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2020-04-25
 * @Description 购物车商品项
 */
@Data
@ApiModel(value = "ShoppingCartItemVO", description = "购物车商品项")
public class ShoppingCartItemVO {
    @ApiModelProperty(value = "商品id", name = "itemId", required = true)
    private String itemId;

    @ApiModelProperty(value = "商品图片地址", name = "itemImageUrl", required = true)
    private String itemImageUrl;

    @ApiModelProperty(value = "商品规格id", name = "itemSpecId", required = true)
    private String itemSpecId;

    @ApiModelProperty(value = "商品规格名称", name = "itemSpecName", required = true)
    private String itemSpecName;

    @ApiModelProperty(value = "优惠价", name = "priceDiscount", required = true)
    private long priceDiscount;

    @ApiModelProperty(value = "原价", name = "priceNormal", required = true)
    private long priceNormal;

    @ApiModelProperty(value = "购买数量", name = "buyCount", required = true)
    private int buyCount;
}
