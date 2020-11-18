package william.eshop.controller;

import static java.util.stream.Collectors.toList;
import static william.eshop.rest.ResultCode.ITEM_NOT_EXISTS;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import william.eshop.model.item.Item;
import william.eshop.rest.CommonRestResponse;
import william.eshop.service.item.ItemService;
import william.eshop.vo.item.ItemDetailVO;
import william.eshop.vo.item.ItemSimpleVO;

/**
 * @Author zhangshenao
 * @Date 2019-12-02
 * @Description
 */
@Api(value = "商品相关接口", tags = "商品相关接口")
@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/{itemId}")
    @ApiOperation(value = "商品详情", httpMethod = "GET")
    public CommonRestResponse itemDetail(@PathVariable("itemId") String itemId) {
        Optional<ItemDetailVO> item = itemService.getById(itemId);
        if (!item.isPresent()) {
            return CommonRestResponse.error(ITEM_NOT_EXISTS);
        }
        return CommonRestResponse.ok(item.get());
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询所有商品列表", httpMethod = "GET")
    public CommonRestResponse<List<ItemDetailVO>> listAll() {
        List<ItemDetailVO> items = Optional.ofNullable(itemService.listAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(Item::toVO)
                .collect(toList());
        return CommonRestResponse.ok(items);
    }

    @GetMapping("/listByRootCategory/{rootCategoryId}")
    @ApiOperation(value = "根据一级分类查询商品列表", httpMethod = "GET")
    public CommonRestResponse<List<ItemDetailVO>> listByRootCategory(
            @PathVariable("rootCategoryId") long rootCategoryId) {
        List<ItemDetailVO> items = Optional.ofNullable(itemService.listByRootCategory(rootCategoryId))
                .orElse(Collections.emptyList())
                .stream()
                .map(Item::toVO)
                .collect(toList());
        return CommonRestResponse.ok(items);
    }

    @GetMapping("/search")
    @ApiOperation(value = "根据关键词搜索", httpMethod = "GET")
    public CommonRestResponse<List<ItemSimpleVO>> search(
            @ApiParam(name = "keyWord", value = "关键词", required = true) @RequestParam("keyWord") String keyWord,
            @ApiParam(name = "sort", value = "排序方式 1=时间降序 2=销量降序 3=价格升序", required = true) @RequestParam("sort")
                    int sort) {
        List<ItemSimpleVO> items = itemService.search(keyWord, sort);
        return CommonRestResponse.ok(items);
    }

    @GetMapping("/listByCategory/{categoryId}")
    @ApiOperation(value = "查询分类下的所有商品", httpMethod = "GET")
    public CommonRestResponse<List<ItemSimpleVO>> listByCategory(@PathVariable("categoryId") int categoryId,
            @ApiParam(name = "sort", value = "排序方式 1=时间降序 2=销量降序 3=价格升序", required = true) @RequestParam("sort")
                    int sort) {
        List<ItemSimpleVO> items = itemService.listByCategory(categoryId, sort);
        return CommonRestResponse.ok(items);
    }
}
