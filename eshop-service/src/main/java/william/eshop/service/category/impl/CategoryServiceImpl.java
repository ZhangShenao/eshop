package william.eshop.service.category.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import william.eshop.constants.CategoryLevel;
import william.eshop.mapper.category.CategoryMapper;
import william.eshop.model.category.Category;
import william.eshop.service.category.CategoryService;
import william.eshop.vo.carousel.CategoryVO;

/**
 * @Author zhangshenao
 * @Date 2019-11-27
 * @Description
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVO> listByLevel(CategoryLevel level) {
        return Optional.ofNullable(categoryMapper.listByLevel(level.getLevel()))
                .orElse(Collections.emptyList())
                .stream()
                .map(Category::toVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryVO> subCategories(long categoryId) {
        return Optional.ofNullable(categoryMapper.subCategories(categoryId))
                .orElse(Collections.emptyList())
                .stream()
                .map(Category::toVO)
                .collect(Collectors.toList());
    }
}
