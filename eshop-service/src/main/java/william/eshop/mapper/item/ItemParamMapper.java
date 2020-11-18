package william.eshop.mapper.item;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import william.eshop.model.item.ItemParam;

/**
 * @Author zhangshenao
 * @Date 2020-04-21
 * @Description 商品参数Mapper
 */
public interface ItemParamMapper extends Mapper<ItemParam>, MySqlMapper<ItemParam> {
    /**
     * 查询商品的参数
     */
    ItemParam findByItemId(@Param("itemId") String itemId);
}
