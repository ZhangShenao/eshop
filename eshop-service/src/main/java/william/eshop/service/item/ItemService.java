package william.eshop.service.item;

import java.util.List;
import java.util.Optional;

import william.eshop.model.item.Item;
import william.eshop.model.item.ItemImage;
import william.eshop.model.item.ItemParam;
import william.eshop.model.item.ItemSpec;
import william.eshop.vo.item.ItemDetailVO;
import william.eshop.vo.item.ItemSimpleVO;

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

    /**
     * 查询商品详情
     */
    Optional<ItemDetailVO> getById(String itemId);

    /**
     * 商品图片列表
     */
    List<ItemImage> listImage(String itemId);

    /**
     * 商品参数
     */
    Optional<ItemParam> param(String itemId);

    /**
     * 商品规格列表
     */
    List<ItemSpec> spec(String itemId);

    /**
     * 关键词搜索
     */
    List<ItemSimpleVO> search(String keyWord, int sort);

    /**
     * 查询分类下的所有商品
     */
    List<ItemSimpleVO> listByCategory(int categoryId, int sort);

    /**
     * 根据id查询规格列表
     */
    List<ItemSpec> listSpecInIds(List<String> specIds);
}
