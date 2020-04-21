package william.eshop.model.item;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import william.eshop.vo.item.ItemParamVO;

/**
 * @Author zhangshenao
 * @Date 2020-04-21
 * @Description 商品参数
 */
@Data
@Table(name = "items_param")
public class ItemParam {
    @Id
    private String id;  //主键

    private String itemId;  //商品id

    private String producePlace;    //产地，例：中国江苏

    private String footPeriod;  //保质期，例：180天

    private String brand;   //品牌名，例：三只大灰狼

    private String factoryName; //生产厂名，例：大灰狼工厂

    private String factoryAddress;  //生产厂址，例：大灰狼生产基地

    private String packagingMethod; //包装方式，例：袋装

    private String weight;  //规格重量，例：35g

    private String storageMethod;   //存储方法，例：常温5~25°

    private String eatMethod;   //食用方式，例：开袋即食

    private Date createdTime;   //创建时间

    private Date updatedTime; //更新时间

    public ItemParamVO toVO() {
        ItemParamVO vo = new ItemParamVO();
        BeanUtils.copyProperties(this, vo);
        return vo;
    }
}
