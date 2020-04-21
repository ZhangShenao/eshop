package william.eshop.mapper.item;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import william.eshop.model.item.ItemImage;

/**
 * @Author zhangshenao
 * @Date 2020-04-21
 * @Description 商品图片Mapper
 */
public interface ItemImageMapper extends Mapper<ItemImage>, MySqlMapper<ItemImage> {

    List<ItemImage> listByItemId(@Param("itemId") String itemId);
}
