package william.eshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import william.eshop.mapper.ItemMapper;
import william.eshop.model.Item;
import william.eshop.service.ItemService;

/**
 * @Author zhangshenao
 * @Date 2019-12-02
 * @Description
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Item> listAll() {
        return itemMapper.selectAll();
    }

    @Override
    public List<Item> listByRootCategory(long rootCategoryId) {
        return itemMapper.listByRootCategory(rootCategoryId);
    }

    @Override
    public Optional<Item> getById(String itemId) {
        return Optional.ofNullable(itemMapper.selectByPrimaryKey(itemId));
    }
}
