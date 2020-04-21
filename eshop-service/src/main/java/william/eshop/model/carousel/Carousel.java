package william.eshop.model.carousel;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import william.eshop.vo.carousel.CarouselVO;

/**
 * @Author zhangshenao
 * @Date 2019-11-27
 * @Description 轮播图实体类
 */
@Data
@Table(name = "carousel")
public class Carousel {
    @Id
    private String id;  //主键

    private String imageUrl;    //图片地址

    private String backgroundColor; //背景色

    private String itemId;  //商品id

    private String catId; //商品分类id

    private int type;   //轮播图类型,用于判断,可以根据商品id或者分类进行页面跳转 1=商品 2=分类

    private int sort;   //轮播图展示顺序

    private int isShow; //是否展示

    private Date createTime;    //创建时间
    private Date updateTime;    //更新时间

    public CarouselVO toVO() {
        CarouselVO vo = new CarouselVO();
        BeanUtils.copyProperties(this, vo);
        return vo;
    }
}
