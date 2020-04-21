package william.eshop.service;

import java.util.List;

import william.eshop.constants.CategoryLevel;
import william.eshop.vo.CategoryVO;

/**
 * @Author zhangshenao
 * @Date 2019-11-27
 * @Description 商品类型服务
 */
public interface CategoryService {
    /**
     * 按照级别查询类型列表
     */
    List<CategoryVO> listByLevel(CategoryLevel level);

    /**
     * 获取子分类列表
     */
    List<CategoryVO> subCategories(long categoryId);
}
