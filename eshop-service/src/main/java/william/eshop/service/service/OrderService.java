package william.eshop.service.service;

import java.util.Optional;

import william.eshop.constants.OrderStatus;
import william.eshop.param.CommitOrderParam;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description 订单服务
 */
public interface OrderService {
    /**
     * 创建订单,如果创建成功,则返回订单id
     */
    Optional<String> create(CommitOrderParam param);

    boolean updateOrderStatus(String orderId, OrderStatus orderStatus);
}
