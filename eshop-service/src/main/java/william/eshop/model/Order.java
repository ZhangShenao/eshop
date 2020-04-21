package william.eshop.model;

import static william.eshop.constants.CommonFlag.NO;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import william.eshop.param.CommitOrderParam;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description 订单实体类
 */
@Data
@Table(name = "orders")
public class Order {
    @Id
    private String id;  //订单主键,同时也是订单编号

    private String userId;  //下单用户id

    private String receiverName;    //收货人姓名快照

    private String receiverMobile;  //收货人手机号快照

    private String receiverAddress; //收货地址快照

    private long totalAmount;  //订单总价格

    private long realPayAmount; //实际支付总价格

    private long postAmount;    //邮费. 默认可以为零，代表包邮

    private int payMethod;  //支付方式

    private String leftMsg; //买家留言

    private String extand;  //扩展字段

    private int isComment;  //买家是否评价;1：已评价，0：未评价

    private int isDelete;   //逻辑删除状态;1: 删除 0:未删除

    private Date createdTime;   //创建时间（成交时间）

    private Date updatedTime;   //更新时间

    public static Order newInstance(CommitOrderParam param) {
        Order order = new Order();
        BeanUtils.copyProperties(param, order);
        order.id = UUID.randomUUID().toString();
        order.isComment = NO.getValue();
        order.isDelete = NO.getValue();
        order.createdTime = new Date();
        order.updatedTime = new Date();
        order.postAmount = 0L;      //所有商品均包邮,邮费为0
        return order;
    }
}
