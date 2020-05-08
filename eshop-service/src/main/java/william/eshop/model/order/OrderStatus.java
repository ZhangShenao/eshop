package william.eshop.model.order;

import static william.eshop.constants.OrderStatusEnum.WAIT_PAY;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import william.eshop.constants.OrderStatusEnum;
import william.eshop.vo.vo.OrderStatusVO;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description 订单状态实体类
 */
@Data
@Table(name = "order_status")
public class OrderStatus {
    @Id
    private String orderId;    //订单id,对应订单表的主键id

    private int orderStatus;    //订单状态

    private Date createdTime;   //订单创建时间;对应[10:待付款]状态

    private Date payTime;   //支付成功时间;对应[20:已付款，待发货]状态

    private Date deliverTime;   //发货时间;对应[30：已发货，待收货]状态

    private Date successTime;   //交易成功时间;对应[40：交易成功]状态

    private Date closeTime;   //交易关闭时间;对应[50：交易关闭]状态

    private Date commentTime;   //留言时间;用户在交易成功后的留言时间

    public static OrderStatus newInstance(String orderId) {
        OrderStatus model = new OrderStatus();
        model.orderId = orderId;
        model.orderStatus = WAIT_PAY.getValue();
        model.createdTime = new Date();
        return model;
    }

    public OrderStatusVO toVO() {
        OrderStatusVO vo = new OrderStatusVO();
        vo.setOrderId(orderId);
        vo.setStatus(orderStatus);
        OrderStatusEnum statusEnum = OrderStatusEnum.findByValue(orderStatus);
        vo.setDesc(statusEnum.getName());
        return vo;
    }
}
