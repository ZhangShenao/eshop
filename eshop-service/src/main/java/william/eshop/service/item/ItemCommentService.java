package william.eshop.service.item;

import java.util.List;

import william.eshop.model.item.ItemComment;

/**
 * @Author zhangshenao
 * @Date 2019-12-03
 * @Description
 */
public interface ItemCommentService {
    /**
     * 查询指定商品的评价列表
     * @param itemId 商品id
     */
    List<ItemComment> listByItem(String itemId);
}
