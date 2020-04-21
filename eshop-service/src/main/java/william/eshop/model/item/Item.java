package william.eshop.model.item;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import william.eshop.vo.item.ItemDetailVO;

/**
 * @Author zhangshenao
 * @Date 2019-12-02
 * @Description 商品实体类
 */
@Data
@Table(name = "items")
public class Item {
    @Id
    private String id;  //主键

    private String itemName;    //商品名称

    private long catId; //直属分类id

    private long rootCatId; //一级分类id

    private long sellCounts;    //累计销量

    private int onOffStatus;      //上下架状态 1=上架 2=下架

    private String content; //商品内容

    private Date createdTime;   //创建时间

    private Date updatedTime;   //更新时间

    public ItemDetailVO toVO() {
        ItemDetailVO vo = new ItemDetailVO();
        BeanUtils.copyProperties(this, vo);
        return vo;
    }
}
