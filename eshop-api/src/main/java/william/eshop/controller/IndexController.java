package william.eshop.controller;

import static william.eshop.constants.CarouselShowStatus.SHOW;
import static william.eshop.constants.CategoryLevel.LEVEL_1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import william.eshop.rest.CommonRestResponse;
import william.eshop.service.carousel.CarouselService;
import william.eshop.service.category.CategoryService;
import william.eshop.vo.carousel.CarouselVO;
import william.eshop.vo.carousel.CategoryVO;

/**
 * @Author zhangshenao
 * @Date 2019-11-27
 * @Description 首页展示相关API
 */
@Api(value = "首页展示的相关接口", tags = "首页展示的相关接口")
@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/carousels")
    @ApiOperation(value = "首页轮播图列表", httpMethod = "GET")
    public CommonRestResponse<List<CarouselVO>> showAllCarousel() {
        return CommonRestResponse.ok(carouselService.listByShowStatus(SHOW));
    }

    @GetMapping("/category/root/list")
    @ApiOperation(value = "一级分类列表", httpMethod = "GET")
    public CommonRestResponse<List<CategoryVO>> rootCategories() {
        return CommonRestResponse.ok(categoryService.listByLevel(LEVEL_1));
    }

    @GetMapping("/category/{categoryId}/sub")
    @ApiOperation(value = "子分类列表", httpMethod = "GET")
    public CommonRestResponse<List<CategoryVO>> subCategories(
            @ApiParam(name = "categoryId", value = "父分类id", required = true) @PathVariable long categoryId) {
        return CommonRestResponse.ok(categoryService.subCategories(categoryId));

    }
}
