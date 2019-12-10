package william.eshop.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import william.eshop.model.OrderItem;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description 订单项Mapper
 */
public interface OrderItemMapper extends Mapper<OrderItem>, MySqlMapper<OrderItem> {
}
