package william.eshop.vo.item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2019-12-03
 * @Description
 */
@Data
@ApiModel(value = "ItemCommentVO", description = "商品评价")
public class ItemCommentVO {
    @ApiModelProperty(value = "评价id", name = "id", required = true)
    private String id;

    @ApiModelProperty(value = "评价作者id", name = "userId", required = true)
    private String userId;

    @ApiModelProperty(value = "商品id", name = "itemId", required = true)
    private String itemId;

    @ApiModelProperty(value = "商品名称", name = "itemName", required = true)
    private String itemName;

    @ApiModelProperty(value = "商品规格id", name = "itemSpecId", required = true)
    private String itemSpecId;

    @ApiModelProperty(value = "商品规格名称", name = "specName", required = true)
    private String specName;

    @ApiModelProperty(value = "评价等级", name = "commentLevel", required = true)
    private int commentLevel;

    @ApiModelProperty(value = "评价内容", name = "content", required = true)
    private String content;
}
