package william.eshop.mapper.category;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import william.eshop.model.category.Category;

/**
 * @Author zhangshenao
 * @Date 2019-11-27
 * @Description 商品类别Mapper
 */
public interface CategoryMapper extends Mapper<Category>, MySqlMapper<Category> {
    List<Category> listByLevel(@Param("level") int level);

    List<Category> subCategories(@Param("categoryId") long categoryId);
}
