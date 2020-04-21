package william.eshop.service;

import java.util.List;
import java.util.Optional;

import william.eshop.model.Item;

/**
 * @Author zhangshenao
 * @Date 2019-12-02
 * @Description 商品服务
 */
public interface ItemService {
    /**
     * 查询所有商品
     */
    List<Item> listAll();

    /**
     * 根据一级分类查询商品
     */
    List<Item> listByRootCategory(long rootCategoryId);

    Optional<Item> getById(String itemId);
}
