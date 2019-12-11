package william.eshop.model;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import william.eshop.constants.UserConstants;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description 订单项实体类
 */
@Data
@Table(name = "order_items")
public class OrderItem {
    @Id
    private String id;   //主键id

    private String orderId;  //归属订单id

    private String itemId;  //商品id

    private String itemImg; //商品图片

    private String itemName;    //商品名称

    private String itemSpecId;  //规格id

    private String itemSpecName;    //规格名称

    private long price; //成交价格

    private long buyCounts; //购买数量

    public static OrderItem newInstance(String orderId, ItemSpec itemSpec, long buyCounts) {
        OrderItem model = new OrderItem();
        model.id = UUID.randomUUID().toString();
        model.orderId = orderId;
        model.itemId = itemSpec.getItemId();
        model.itemImg = UserConstants.DEFAULT_USER_FACE;
        model.itemName = itemSpec.getName();
        model.itemSpecId = itemSpec.getId();
        model.itemSpecName = itemSpec.getName();
        model.price = itemSpec.getPriceNormal();
        model.buyCounts = buyCounts;
        return model;
    }
}
