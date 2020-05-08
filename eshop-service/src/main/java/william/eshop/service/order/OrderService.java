package william.eshop.service.order;

import java.util.List;
import java.util.Optional;

import william.eshop.constants.OrderStatusEnum;
import william.eshop.param.order.CommitOrderParam;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description 订单服务
 */
public interface OrderService {
    /**
     * 创建订单,如果创建成功,则返回订单id
     */
    Optional<String> create(String userId, CommitOrderParam param);

    /**
     * 更新订单状态
     */
    boolean updateOrderStatus(String orderId, OrderStatusEnum orderStatus);

    /**
     * 查询订单状态
     */
    Optional<william.eshop.model.order.OrderStatus> queryOrderStatus(String orderId);

    /**
     * 查询所有订单状态
     */
    List<william.eshop.model.order.OrderStatus> allOrderStatus();

    /**
     * 关闭订单
     */
    boolean closeOrder(String orderId);
}
