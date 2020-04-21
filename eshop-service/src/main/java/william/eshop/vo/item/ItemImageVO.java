package william.eshop.vo.item;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2020-04-21
 * @Description 商品图片信息
 */
@Data
@ApiModel(value = "ItemImageVO", description = "商品图片信息")
public class ItemImageVO {
    private String id;

    private String itemId;  //商品id

    private String url; //图片地址

    private int sort;   //图片顺序,从小到大

    private int isMain; //是否主图
}
