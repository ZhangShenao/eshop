package william.eshop.model.item;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import william.eshop.vo.item.ItemImageVO;

/**
 * @Author zhangshenao
 * @Date 2020-04-21
 * @Description 商品图片
 */
@Data
@Table(name = "items_img")
public class ItemImage {
    @Id
    private String id;  //主键

    private String itemId;  //商品id

    private String url; //图片地址

    private int sort;   //图片顺序,从小到大

    private int isMain; //是否主图

    private Date createdTime;   //创建时间

    private Date updatedTime;   //更新时间

    public ItemImageVO toVO() {
        ItemImageVO vo = new ItemImageVO();
        BeanUtils.copyProperties(this, vo);
        return vo;
    }
}
