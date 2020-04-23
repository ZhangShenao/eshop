package william.eshop.service.item;

import java.util.List;

import william.eshop.model.item.ItemComment;
import william.eshop.vo.item.ItemCommentStatisticsVO;

/**
 * @Author zhangshenao
 * @Date 2019-12-03
 * @Description
 */
public interface ItemCommentService {
    /**
     * 查询指定商品的评价列表
     */
    List<ItemComment> listByItem(String itemId);

    /**
     * 统计商品的评价数量
     */
    ItemCommentStatisticsVO countByItem(String itemId);
}
