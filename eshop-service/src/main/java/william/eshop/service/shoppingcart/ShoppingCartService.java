package william.eshop.service.shoppingcart;

import william.eshop.param.shoppingcart.ShoppingCartParam;
import william.eshop.vo.shoppingcart.ShoppingCartVO;

/**
 * @Author zhangshenao
 * @Date 2020-04-25
 * @Description 购物车服务
 */
public interface ShoppingCartService {
    /**
     * 查询购物车商品详情
     */
    ShoppingCartVO shoppingCartInfo(ShoppingCartParam param);
}
