package william.eshop.vo.item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2020-04-23
 * @Description 商品评价数量统计
 */
@Data
@ApiModel(value = "ItemCommentStatisticsVO", description = "商品评价数量统计")
public class ItemCommentStatisticsVO {
    @ApiModelProperty(value = "总评价数", name = "total", required = true)
    private long total;

    @ApiModelProperty(value = "好评数", name = "good", required = true)
    private long good;

    @ApiModelProperty(value = "中评数", name = "normal", required = true)
    private long normal;

    @ApiModelProperty(value = "差评数", name = "bad", required = true)
    private long bad;
}
