package william.eshop.mapper.order;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import william.eshop.model.order.Order;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description 订单Mapper
 */
public interface OrderMapper extends Mapper<Order>, MySqlMapper<Order> {
}
