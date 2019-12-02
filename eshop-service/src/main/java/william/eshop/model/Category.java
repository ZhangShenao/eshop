package william.eshop.model;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import william.eshop.vo.CategoryVO;

/**
 * @Author zhangshenao
 * @Date 2019-11-27
 * @Description 商品类别实体类
 */
@Data
@Table(name = "items")
public class Category {
    @Id
    private long id;

    private String name;

    private int type;

    private long fatherId;

    private String logo;

    private String slogan;

    private String catImage;

    private String bgColor;

    public CategoryVO toVO() {
        CategoryVO vo = new CategoryVO();
        BeanUtils.copyProperties(this, vo);
        return vo;
    }
}
