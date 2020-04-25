package william.eshop.param.shoppingcart;

import java.util.Iterator;
import java.util.List;

import org.springframework.util.CollectionUtils;

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
    @ApiModelProperty(value = "商品项", name = "items", required = true)
    private List<ShoppingCartItemParam> items;

    public boolean isIllegal() {
        return CollectionUtils.isEmpty(items);
    }

    public void remove(String itemSpecId) {
        Iterator<ShoppingCartItemParam> iterator = items.iterator();
        while (iterator.hasNext()) {
            ShoppingCartItemParam item = iterator.next();
            if (item.getItemSpecId().equalsIgnoreCase(itemSpecId)) {
                iterator.remove();
                return;
            }
        }
    }
}
