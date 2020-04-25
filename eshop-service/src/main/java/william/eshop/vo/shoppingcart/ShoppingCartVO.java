package william.eshop.vo.shoppingcart;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2020-04-25
 * @Description 购物车信息
 */
@Data
@ApiModel(value = "ShoppingCartVO", description = "购物车信息")
public class ShoppingCartVO {
    @ApiModelProperty(value = "商品项", name = "items", required = true)
    private List<ShoppingCartItemVO> items;

    @ApiModelProperty(value = "总金额", name = "totalAmount", required = true)
    private long totalAmount;

    @ApiModelProperty(value = "总数量", name = "totalCount", required = true)
    private int totalCount;

    public static ShoppingCartVO valueOf(List<ShoppingCartItemVO> items) {
        ShoppingCartVO vo = new ShoppingCartVO();
        vo.items = items;
        vo.items.forEach(x -> {
            vo.totalAmount += x.getBuyCount();
            vo.totalAmount += x.getPriceDiscount();
        });
        return vo;
    }
}
