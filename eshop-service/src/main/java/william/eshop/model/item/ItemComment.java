package william.eshop.model.item;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import william.eshop.vo.item.ItemCommentVO;

/**
 * @Author zhangshenao
 * @Date 2019-12-03
 * @Description 商品评价实体类
 */
@Data
@Table(name = "items_comments")
public class ItemComment {
    @Id
    private String id;

    private String userId;

    private String itemId;

    private String itemName;

    private String itemSpecId;

    private String sepcName;

    private int commentLevel;

    private String content;

    private Date createdTime;

    private Date updatedTime;

    public ItemCommentVO toVO() {
        ItemCommentVO vo = new ItemCommentVO();
        BeanUtils.copyProperties(this, vo);
        return vo;
    }
}
