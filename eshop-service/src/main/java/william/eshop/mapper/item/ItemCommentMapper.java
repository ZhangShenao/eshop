package william.eshop.mapper.item;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import william.eshop.model.item.ItemComment;

/**
 * @Author zhangshenao
 * @Date 2019-12-03
 * @Description 商品评价Mapper
 */
public interface ItemCommentMapper extends Mapper<ItemComment>, MySqlMapper<ItemComment> {
    /**
     * 查询指定商品的评价列表
     */
    List<ItemComment> listByItemId(@Param("itemId") String itemId);

    /**
     * 查询商品的评价总数
     */
    Long countByItem(@Param("itemId") String itemId);

    /**
     * 根据评价等级,查询商品的评价总数
     */
    Long countByItemAndLevel(@Param("itemId") String itemId, @Param("level") int level);
}
