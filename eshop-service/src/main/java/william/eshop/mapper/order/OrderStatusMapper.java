package william.eshop.mapper.order;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import william.eshop.model.order.OrderStatus;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description 订单状态Mapper
 */
public interface OrderStatusMapper extends Mapper<OrderStatus>, MySqlMapper<OrderStatus> {
}
