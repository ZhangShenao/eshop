package william.eshop.vo.item;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2019-12-02
 * @Description 商品详情信息
 */
@Data
@ApiModel(value = "ItemDetailVO", description = "商品详情信息")
public class ItemDetailVO {
    @ApiModelProperty(value = "商品id", name = "id", required = true)
    private String id;

    @ApiModelProperty(value = "商品名称", name = "itemName", required = true)
    private String itemName;

    @ApiModelProperty(value = "商品分类id", name = "catId", required = true)
    private long carId;

    @ApiModelProperty(value = "商品根分类id", name = "rootCatId", required = true)
    private long rootCatId;

    @ApiModelProperty(value = "商品销量", name = "sellCounts", required = true)
    private long sellCounts;

    @ApiModelProperty(value = "商品上/下架状态", name = "onOffStatus", required = true)
    private int onOffStatus;

    @ApiModelProperty(value = "商品描述信息", name = "content", required = true)
    private String content;

    @ApiModelProperty(value = "商品图片列表", name = "images", required = true)
    private List<ItemImageVO> images;

    @ApiModelProperty(value = "商品参数", name = "param", required = true)
    private ItemParamVO param;

    @ApiModelProperty(value = "商品规格列表", name = "specs", required = true)
    private List<ItemSpecVO> specs;
}
