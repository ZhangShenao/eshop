package william.eshop.model.item;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import william.eshop.vo.item.ItemSpecVO;
import william.eshop.vo.shoppingcart.ShoppingCartItemVO;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description 商品规格实体类
 */
@Data
@Table(name = "items_spec")
public class ItemSpec {
    @Id
    private String id; //商品规格id

    private String itemId; //商品id

    private String name;    //规格名称

    private long stock; //库存

    private double discounts;   //折扣力度

    private long priceDiscount; //优惠价

    private long priceNormal;   //原价

    private Date createdTime;   //创建时间

    private Date updatedTime;   //更新时间

    public ItemSpecVO toVO() {
        ItemSpecVO vo = new ItemSpecVO();
        BeanUtils.copyProperties(this, vo);
        return vo;
    }

    public ShoppingCartItemVO toShoppingCartItemVO() {
        ShoppingCartItemVO vo = new ShoppingCartItemVO();
        BeanUtils.copyProperties(this, vo);
        vo.setItemSpecId(id);
        vo.setItemSpecName(name);
        return vo;
    }
}
