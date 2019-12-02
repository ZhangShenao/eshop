package william.eshop.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import william.eshop.vo.ItemVO;

/**
 * @Author zhangshenao
 * @Date 2019-12-02
 * @Description 商品实体类
 */
@Data
@Table(name = "items")
public class Item {
    @Id
    private String id;

    private String itemName;

    private long catId;

    private long rootCatId;

    private long sellCounts;

    private int onOffStatus;

    private String content;

    private Date createdTime;

    private Date updatedTime;

    public ItemVO toVO() {
        ItemVO vo = new ItemVO();
        BeanUtils.copyProperties(this, vo);
        return vo;
    }
}
