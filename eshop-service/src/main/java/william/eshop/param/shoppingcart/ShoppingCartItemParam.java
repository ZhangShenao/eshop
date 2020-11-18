package william.eshop.param.shoppingcart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2020-04-25
 * @Description 购物车的商品项
 */
@Data
@ApiModel(value = "ShoppingCartItem", description = "购物车的商品项")
public class ShoppingCartItemParam {
    @ApiModelProperty(value = "商品规格id", name = "itemSpecId", required = true)
    private String itemSpecId;

    @ApiModelProperty(value = "购买数量", name = "buyCounts", required = true)
    private Integer buyCounts;
}
