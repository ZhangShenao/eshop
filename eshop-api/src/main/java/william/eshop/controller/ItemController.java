package william.eshop.controller;

import static william.eshop.rest.ResultCode.ITEM_NOT_EXISTS;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import william.eshop.model.Item;
import william.eshop.rest.CommonRestResponse;
import william.eshop.service.ItemService;
import william.eshop.vo.ItemVO;

/**
 * @Author zhangshenao
 * @Date 2019-12-02
 * @Description
 */
@Api(value = "商品相关接口")
@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/{itemId}")
    @ApiOperation(value = "商品详情", httpMethod = "GET")
    public CommonRestResponse itemDetail(@PathVariable("itemId") String itemId) {
        Optional<Item> item = itemService.getById(itemId);
        if (!item.isPresent()) {
            return CommonRestResponse.error(ITEM_NOT_EXISTS);
        }
        return CommonRestResponse.ok(item.get());
    }

    @GetMapping("/listAll")
    @ApiOperation(value = "查询所有商品列表", httpMethod = "GET")
    public CommonRestResponse<List<ItemVO>> listAll() {
        List<ItemVO> items = Optional.ofNullable(itemService.listAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(Item::toVO)
                .collect(Collectors.toList());
        return CommonRestResponse.ok(items);
    }

    @GetMapping("/listByRootCategory/{rootCategoryId}")
    @ApiOperation(value = "根据一级分类查询商品列表", httpMethod = "GET")
    public CommonRestResponse<List<ItemVO>> listByRootCategory(
            @PathVariable("rootCategoryId") long rootCategoryId) {
        List<ItemVO> items = Optional.ofNullable(itemService.listByRootCategory(rootCategoryId))
                .orElse(Collections.emptyList())
                .stream()
                .map(Item::toVO)
                .collect(Collectors.toList());
        return CommonRestResponse.ok(items);
    }
}
