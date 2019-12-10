package william.eshop.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description 商品规格请求参数
 */
@Data
@ApiModel(value = "ItemSpecParam", description = "商品规格请求参数")
public class ItemSpecParam {
    @ApiModelProperty(value = "商品规格id", name = "itemSpecId", required = true)
    private String itemSpecId;

    @ApiModelProperty(value = "购买数量", name = "count", required = true)
    private int count;
}
