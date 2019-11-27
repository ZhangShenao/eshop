package william.eshop.controller;

import static william.eshop.constants.CarouselShowStatus.SHOW;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import william.eshop.model.Carousel;
import william.eshop.rest.CommonRestResponse;
import william.eshop.service.CarouselService;
import william.eshop.vo.CarouselVO;

/**
 * @Author zhangshenao
 * @Date 2019-11-27
 * @Description 首页展示相关API
 */
@Api(value = "首页展示的相关接口")
@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private CarouselService carouselService;

    @GetMapping("/carousels")
    @ApiOperation(value = "首页轮播图列表", httpMethod = "GET")
    public CommonRestResponse<List<CarouselVO>> showAllCarousel() {
        List<CarouselVO> carousels = carouselService.listByShowStatus(SHOW).stream()
                .map(Carousel::toVO)
                .collect(Collectors.toList());
        return CommonRestResponse.ok(carousels);
    }
}
