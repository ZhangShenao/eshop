package william.eshop.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2019-12-07
 * @Description 购物车请求参数
 */
@Data
@ApiModel(value = "ShoppingCartParam", description = "购物车请求参数")
public class ShoppingCartParam {
    @ApiModelProperty(value = "商品id", name = "itemId", required = true)
    private String itemId;

    @ApiModelProperty(value = "商品图片地址", name = "itemImgUrl", required = true)
    private String itemImgUrl;

    @ApiModelProperty(value = "商品名", name = "itemName", required = true)
    private String itemName;

    @ApiModelProperty(value = "商品规格id", name = "specId", required = true)
    private String specId;

    @ApiModelProperty(value = "商品规格名称", name = "specName", required = true)
    private String specName;

    @ApiModelProperty(value = "购买数量", name = "buyCounts", required = true)
    private Integer buyCounts;

    @ApiModelProperty(value = "商品促销价", name = "priceDiscount", required = true)
    private String priceDiscount;

    @ApiModelProperty(value = "商品原价", name = "priceNormal", required = true)
    private String priceNormal;
}
