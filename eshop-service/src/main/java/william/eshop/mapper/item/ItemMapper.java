package william.eshop.mapper.item;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import william.eshop.model.item.Item;

/**
 * @Author zhangshenao
 * @Date 2019-12-02
 * @Description 商品Mapper
 */
public interface ItemMapper extends Mapper<Item>, MySqlMapper<Item> {
    List<Item> listByRootCategory(@Param("rootCategoryId") long rootCategoryId);

    /**
     * 根据关键词查询
     */
    List<Item> searchByKeyWord(@Param("keyWord") String keyWord);

    /**
     * 根据关键词查询
     */
    List<Item> listByCategory(@Param("categoryId") int categoryId);
}
