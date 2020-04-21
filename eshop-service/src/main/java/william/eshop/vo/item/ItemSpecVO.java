package william.eshop.vo.item;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2020-04-21
 * @Description 商品规格信息
 */
@Data
@ApiModel(value = "ItemSpecVO", description = "商品规格信息")
public class ItemSpecVO {
    private String id; //商品规格id

    private String itemId; //商品id

    private String name;    //规格名称

    private long stock; //库存

    private double discounts;   //折扣力度

    private long priceDiscount; //优惠价

    private long priceNormal;   //原价
}