package william.eshop.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

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

    private String itemId; //商品外键id

    private String name;    //规格名称

    private long stock; //库存

    private double discounts;   //折扣力度

    private long priceDiscount; //优惠价

    private long priceNormal;   //原价

    private Date createdTime;   //创建时间

    private Date updatedTime;   //更新时间
}
