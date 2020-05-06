package william.eshop.service.order.impl;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import william.eshop.mapper.address.UserAddressMapper;
import william.eshop.mapper.item.ItemSpecMapper;
import william.eshop.mapper.order.OrderItemMapper;
import william.eshop.mapper.order.OrderMapper;
import william.eshop.mapper.order.OrderStatusMapper;
import william.eshop.model.address.UserAddress;
import william.eshop.model.item.ItemSpec;
import william.eshop.model.order.Order;
import william.eshop.model.order.OrderItem;
import william.eshop.model.order.OrderStatus;
import william.eshop.model.user.User;
import william.eshop.param.order.CommitOrderParam;
import william.eshop.param.item.ItemSpecParam;
import william.eshop.service.user.UserService;
import william.eshop.service.order.OrderService;

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

    @Autowired
    private UserAddressMapper addressMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)  //增加事务,库存不足时自动回滚
    public Optional<String> create(String userId, CommitOrderParam param) {
        //1. 参数校验
        if (param.isIllegal()) {
            return Optional.empty();
        }

        //2. 下单用户校验
        Optional<User> user = userService.queryById(userId);
        if (!user.isPresent()) {
            log.error("下单用户不存在! userId: {}", userId);
            return Optional.empty();
        }

        //3. 查询用户地址
        UserAddress address = addressMapper.selectByPrimaryKey(param.getAddressId());
        if (address == null || !userId.equals(address.getUserId())) {
            log.error("收货地址不存在! userId: {}", userId);
            return Optional.empty();
        }

        //4. 创建Order
        Order order = Order.newInstance(param, address);

        //5. 创建OrderItem
        Map<String, ItemSpecParam> specs = param.getItemSpecs().stream()
                .collect(toMap(ItemSpecParam::getItemSpecId, p -> p));
        List<ItemSpec> itemSpecs = itemSpecMapper.listInIds(new ArrayList<>(specs.keySet()));
        if (CollectionUtils.isEmpty(itemSpecs)) {
            log.error("下单商品为空! userId: {}", userId);
            return Optional.empty();
        }
        List<OrderItem> orderItems = itemSpecs.stream()
                .map(s -> OrderItem.newInstance(order.getId(), s, specs.get(s.getId()).getCount()))
                .collect(toList());

        //5. 创建OrderStatus
        OrderStatus orderStatus = OrderStatus.newInstance(order.getId());

        //6. 计算订单金额
        calcOrderPrice(order, itemSpecs);

        orderMapper.insert(order);
        orderItems.forEach(i -> orderItemMapper.insert(i));
        orderStatusMapper.insert(orderStatus);

        //7. 扣减商品库存,如果库存不足,则抛出异常,事务回滚,避免出现超卖的情况
        for (ItemSpecParam p : specs.values()) {
            int row = itemSpecMapper.reduceStock(p.getItemSpecId(), p.getCount());
            if (row <= 0) {
                throw new OrderException(String.format("库存不足,无法下单!! itemSpecId: %s", p.getItemSpecId()));
            }
        }
        return Optional.of(order.getId());
    }

    @Override
    public boolean updateOrderStatus(String orderId, william.eshop.constants.OrderStatus orderStatus) {
        OrderStatus model = orderStatusMapper.selectByPrimaryKey(orderId);
        if (model == null) {
            log.error("Order Status Empty! orderId: {}", orderId);
            return false;
        }

        //订单状态校验
        if (model.getOrderStatus() >= orderStatus.getValue()) {
            log.error("Order Status Error! orderId: {}", orderId);
            return false;
        }

        model.setOrderStatus(orderStatus.getValue());
        model.setPayTime(new Date());
        orderStatusMapper.updateByPrimaryKey(model);
        return true;
    }

    @Override
    public Optional<OrderStatus> queryOrderStatus(String orderId) {
        return Optional.ofNullable(orderStatusMapper.selectByPrimaryKey(orderId));
    }

    @Override
    public List<OrderStatus> allOrderStatus() {
        return Optional.ofNullable(orderStatusMapper.selectAll())
                .orElse(Collections.emptyList());
    }

    @Override
    public boolean closeOrder(String orderId) {
        OrderStatus orderStatus = orderStatusMapper.selectByPrimaryKey(orderId);
        if (orderStatus == null) {
            return false;
        }
        orderStatus.setOrderStatus(william.eshop.constants.OrderStatus.CLOSED.getValue());
        orderStatus.setCloseTime(new Date());
        return (orderStatusMapper.updateByPrimaryKey(orderStatus) > 0);
    }

    /**
     * 计算订单金额
     */
    private void calcOrderPrice(Order order, List<ItemSpec> itemSpecs) {
        if (CollectionUtils.isEmpty(itemSpecs)) {
            return;
        }
        long totalAmount = 0L;
        long realPayAmount = 0L;
        for (ItemSpec s : itemSpecs) {
            totalAmount += s.getPriceNormal();
            realPayAmount += s.getPriceDiscount();
        }
        order.setTotalAmount(totalAmount);
        order.setRealPayAmount(realPayAmount);
    }
}
