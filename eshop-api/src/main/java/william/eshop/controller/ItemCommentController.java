package william.eshop.controller;

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
import william.eshop.model.item.ItemComment;
import william.eshop.rest.CommonRestResponse;
import william.eshop.service.item.ItemCommentService;
import william.eshop.vo.item.ItemCommentStatisticsVO;
import william.eshop.vo.item.ItemCommentVO;

/**
 * @Author zhangshenao
 * @Date 2019-12-03
 * @Description 商品评价API
 */
@Api(value = "商品评价相关接口", tags = "商品评价相关接口")
@RestController
@RequestMapping("/comment")
public class ItemCommentController {
    @Autowired
    private ItemCommentService commentService;

    @GetMapping("/countByItem/{itemId}")
    @ApiOperation(value = "商品评价数量统计", httpMethod = "GET")
    public CommonRestResponse<ItemCommentStatisticsVO> countByItem(@PathVariable("itemId") String itemId) {
        return CommonRestResponse.ok(commentService.countByItem(itemId));
    }

    @GetMapping("/listByItem/{itemId}")
    @ApiOperation(value = "查询指定商品的评价列表", httpMethod = "GET")
    public CommonRestResponse<List<ItemCommentVO>> listByItem(@PathVariable("itemId") String itemId) {
        List<ItemCommentVO> comments = Optional.ofNullable(commentService.listByItem(itemId))
                .orElse(Collections.emptyList())
                .stream()
                .map(ItemComment::toVO)
                .collect(Collectors.toList());
        return CommonRestResponse.ok(comments);
    }
}
