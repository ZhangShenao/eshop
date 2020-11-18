package william.eshop.vo.item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2020-04-23
 * @Description 商品简单信息
 */
@Data
@ApiModel(value = "ItemSimpleVO", description = "商品简单信息")
public class ItemSimpleVO {
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

    @ApiModelProperty(value = "商品描述信息", name = "content", required = true)
    private String content;

    @ApiModelProperty(value = "更新时间", name = "updateTime", required = true)
    private long updateTime;

    @ApiModelProperty(value = "主图url", name = "mainImageUrl", required = true)
    private String mainImageUrl;

    @ApiModelProperty(value = "最低价", name = "lowestPrice", required = true)
    private long lowestPrice;
}
