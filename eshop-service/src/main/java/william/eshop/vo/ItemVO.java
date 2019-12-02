package william.eshop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2019-12-02
 * @Description 商品信息VO
 */
@Data
@ApiModel(value = "ItemVO", description = "商品信息")
public class ItemVO {
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
}
