package william.eshop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2019-11-27
 * @Description 商品分类VO
 */
@Data
@ApiModel(value = "CategoryVO", description = "商品分类")
public class CategoryVO {
    @ApiModelProperty(value = "分类id", name = "id", required = true)
    private long id;

    @ApiModelProperty(value = "分类名称", name = "name", required = true)
    private String name;

    @ApiModelProperty(value = "分类级别", name = "level", required = true)
    private int level;

    @ApiModelProperty(value = "分类logo", name = "logo", required = true)
    private String logo;

    @ApiModelProperty(value = "分类slogan", name = "slogan", required = true)
    private String slogan;

    @ApiModelProperty(value = "分类图片", name = "catImage", required = true)
    private String catImage;

    @ApiModelProperty(value = "背景色", name = "bgColor", required = true)
    private String bgColor;
}
