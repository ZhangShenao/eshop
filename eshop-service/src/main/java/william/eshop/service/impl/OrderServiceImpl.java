package william.eshop.service.impl;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;
import william.eshop.exception.OrderException;
import william.eshop.mapper.ItemSpecMapper;
import william.eshop.mapper.OrderItemMapper;
import william.eshop.mapper.OrderMapper;
import william.eshop.mapper.OrderStatusMapper;
import william.eshop.model.ItemSpec;
import william.eshop.model.Order;
import william.eshop.model.OrderItem;
import william.eshop.model.OrderStatus;
import william.eshop.model.User;
import william.eshop.param.CommitOrderParam;
import william.eshop.param.ItemSpecParam;
import william.eshop.service.UserService;
import william.eshop.service.service.OrderService;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private ItemSpecMapper itemSpecMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<String> create(CommitOrderParam param) {
        if (param.isIllegal()) {
            return Optional.empty();
        }
        Optional<User> user = userService.queryById(param.getUserId());
        if (!user.isPresent()) {
            log.error("下单用户不存在! userId: {}", param.getUserId());
            return Optional.empty();
        }
        //1. 创建Order
        Order order = Order.newInstance(param);

        //2. 创建OrderItem
        Map<String, ItemSpecParam> specs =
                param.getItemSpecs().stream().collect(toMap(ItemSpecParam::getItemSpecId, p -> p));
        List<ItemSpec> itemSpecs = itemSpecMapper.listInIds(new ArrayList<>(specs.keySet()));
        if (CollectionUtils.isEmpty(itemSpecs)) {
            return Optional.empty();
        }
        List<OrderItem> orderItems =
                itemSpecs.stream()
                        .map(s -> OrderItem.newInstance(order.getId(), s, specs.get(s.getId()).getCount()))
                        .collect(toList());

        //3. 创建OrderStatus
        OrderStatus orderStatus = OrderStatus.newInstance(order.getId());

        //4. 更新订单金额
        updateOrderPrice(order, orderItems);

        orderMapper.insert(order);
        orderItemMapper.insertList(orderItems);
        orderStatusMapper.insert(orderStatus);

        //5. 扣减商品库存,如果库存不足,则抛出异常,事务回滚,避免出现超卖的情况
        for (ItemSpecParam p : specs.values()) {
            int row = itemSpecMapper.reduceStock(p.getItemSpecId(), p.getCount());
            if (row <= 0) {
                throw new OrderException(String.format("库存不足,无法下单!! itemSpecId: %s", p.getItemSpecId()));
            }
        }
        return Optional.of(order.getId());
    }

    private void updateOrderPrice(Order order, List<OrderItem> orderItems) {
        if (CollectionUtils.isEmpty(orderItems)) {
            return;
        }
        long realPayAmount = orderItems.stream().map(OrderItem::getPrice).reduce(Long::sum).orElse(0L);
        order.setRealPayAmount(realPayAmount);
    }

}
