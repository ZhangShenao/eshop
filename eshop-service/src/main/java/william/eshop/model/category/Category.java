package william.eshop.model.category;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import william.eshop.vo.carousel.CategoryVO;

/**
 * @Author zhangshenao
 * @Date 2019-11-27
 * @Description 商品类别实体类
 */
@Data
@Table(name = "category")
public class Category {
    @Id
    private long id;    //主键

    private String name;    //分类名称

    private int level;  //分类级别,根分类=1

    private long fatherId;  //父分类id

    private String logo;    //图标

    private String slogan;  //口号

    private String catImage;    //分类图

    private String bgColor; //背景颜色

    public CategoryVO toVO() {
        CategoryVO vo = new CategoryVO();
        BeanUtils.copyProperties(this, vo);
        return vo;
    }
}
