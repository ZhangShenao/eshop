package william.eshop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2019-11-27
 * @Description 轮播图VO
 */
@Data
@ApiModel(value = "CarouselVO", description = "首页轮播图")
public class CarouselVO {
    @ApiModelProperty(value = "轮播图id", name = "id", required = true)
    private String id;

    @ApiModelProperty(value = "图片地址", name = "imageUrl", required = true)
    private String imageUrl;

    @ApiModelProperty(value = "背景色", name = "backgroundColor", required = true)
    private String backgroundColor;

    @ApiModelProperty(value = "轮播图商品id", name = "itemId", required = true)
    private String itemId;

    @ApiModelProperty(value = "轮播图商品分类id", name = "catId", required = true)
    private String catId;

    @ApiModelProperty(value = "轮播图类型", name = "type", required = true)
    private int type;

    @ApiModelProperty(value = "轮播图排序", name = "sort", required = true)
    private int sort;
}
