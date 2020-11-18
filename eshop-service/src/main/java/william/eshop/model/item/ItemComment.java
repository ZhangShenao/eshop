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
    private String id;  //主键

    private String userId;  //用户id(用户名须脱敏)

    private String itemId;  //商品id

    private String itemName;    //商品名称

    private String itemSpecId;  //商品规格id,可为空

    private String specName;    //规格名称,可为空

    private int commentLevel;   //评价等级 1=好评 2=中评 3=差评

    private String content;     //评价内容

    private Date createdTime;   //创建时间

    private Date updatedTime;       //更新时间

    public ItemCommentVO toVO() {
        ItemCommentVO vo = new ItemCommentVO();
        BeanUtils.copyProperties(this, vo);
        return vo;
    }
}
