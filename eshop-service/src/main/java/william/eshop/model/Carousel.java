package william.eshop.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import william.eshop.vo.CarouselVO;

/**
 * @Author zhangshenao
 * @Date 2019-11-27
 * @Description 轮播图实体类
 */
@Data
@Table(name = "carousel")
public class Carousel {
    @Id
    private String id;
    private String imageUrl;
    private String backgroundColor;
    private String itemId;
    private String catId;
    private int type;
    private int sort;
    private int isShow;
    private Date createTime;
    private Date updateTime;

    public CarouselVO toVO() {
        CarouselVO vo = new CarouselVO();
        BeanUtils.copyProperties(this, vo);
        return vo;
    }
}
