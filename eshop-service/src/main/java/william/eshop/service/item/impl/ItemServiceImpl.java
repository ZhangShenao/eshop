package william.eshop.service.item.impl;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import william.eshop.mapper.item.ItemImageMapper;
import william.eshop.mapper.item.ItemMapper;
import william.eshop.mapper.item.ItemParamMapper;
import william.eshop.mapper.item.ItemSpecMapper;
import william.eshop.model.item.Item;
import william.eshop.model.item.ItemImage;
import william.eshop.model.item.ItemParam;
import william.eshop.model.item.ItemSpec;
import william.eshop.service.item.ItemService;
import william.eshop.vo.item.ItemDetailVO;
import william.eshop.vo.item.ItemImageVO;
import william.eshop.vo.item.ItemSpecVO;

/**
 * @Author zhangshenao
 * @Date 2019-12-02
 * @Description
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemImageMapper imageMapper;

    @Autowired
    private ItemParamMapper paramMapper;

    @Autowired
    private ItemSpecMapper specMapper;

    @Override
    public List<Item> listAll() {
        return itemMapper.selectAll();
    }

    @Override
    public List<Item> listByRootCategory(long rootCategoryId) {
        return itemMapper.listByRootCategory(rootCategoryId);
    }

    @Override
    public Optional<ItemDetailVO> getById(String itemId) {
        Item model = itemMapper.selectByPrimaryKey(itemId);
        if (model == null) {
            return Optional.empty();
        }

        return Optional.of(buildItemDetail(model));
    }

    @Override
    public List<ItemImage> listImage(String itemId) {
        return Optional.ofNullable(imageMapper.listByItemId(itemId))
                .orElse(Collections.emptyList())
                .stream()
                .sorted(Comparator.comparingInt(ItemImage::getSort))
                .collect(toList());
    }

    @Override
    public Optional<ItemParam> param(String itemId) {
        return Optional.ofNullable(paramMapper.findByItemId(itemId));
    }

    @Override
    public List<ItemSpec> spec(String itemId) {
        return specMapper.listByItemId(itemId);
    }

    private ItemDetailVO buildItemDetail(Item item) {
        //基本信息
        ItemDetailVO vo = item.toVO();

        //图片信息
        List<ItemImageVO> images = listImage(item.getId()).stream().map(ItemImage::toVO).collect(toList());
        vo.setImages(images);

        //参数信息
        param(item.getId()).ifPresent(p -> vo.setParam(p.toVO()));

        //规格信息
        List<ItemSpecVO> specs = spec(item.getId()).stream().map(ItemSpec::toVO).collect(toList());
        vo.setSpecs(specs);
        return vo;
    }
}
