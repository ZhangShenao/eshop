package william.eshop.service.shoppingcart.impl;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import william.eshop.model.item.ItemImage;
import william.eshop.model.item.ItemSpec;
import william.eshop.param.shoppingcart.ShoppingCartItemParam;
import william.eshop.param.shoppingcart.ShoppingCartParam;
import william.eshop.service.item.ItemService;
import william.eshop.service.shoppingcart.ShoppingCartService;
import william.eshop.vo.shoppingcart.ShoppingCartItemVO;
import william.eshop.vo.shoppingcart.ShoppingCartVO;

/**
 * @Author zhangshenao
 * @Date 2020-04-25
 * @Description 购物车服务
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ItemService itemService;

    @Override
    public ShoppingCartVO shoppingCartInfo(ShoppingCartParam param) {
        List<String> specIds = param.getItems().stream()
                .map(ShoppingCartItemParam::getItemSpecId)
                .collect(toList());
        List<ShoppingCartItemVO> items = itemService.listSpecInIds(specIds).stream()
                .map(ItemSpec::toShoppingCartItemVO)
                .collect(toList());
        fillItemImage(items);
        updateBuyCount(param, items);
        return ShoppingCartVO.valueOf(items);
    }

    private void fillItemImage(List<ShoppingCartItemVO> items) {
        items.forEach(x -> {
            List<ItemImage> images = itemService.listImage(x.getItemId());
            if (!CollectionUtils.isEmpty(images)) {
                x.setItemImageUrl(images.get(0).getUrl());
            }
        });
    }

    private void updateBuyCount(ShoppingCartParam param, List<ShoppingCartItemVO> items) {
        Map<String, Integer> specId2BuyCount = param.getItems().stream()
                .collect(toMap(ShoppingCartItemParam::getItemSpecId, ShoppingCartItemParam::getBuyCounts));
        items.forEach(x -> {
            String specId = x.getItemSpecId();
            Optional.ofNullable(specId2BuyCount.get(specId)).ifPresent(x::setBuyCount);
        });
    }
}
