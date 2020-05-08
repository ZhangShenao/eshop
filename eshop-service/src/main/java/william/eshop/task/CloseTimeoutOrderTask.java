package william.eshop.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import william.eshop.model.order.OrderStatus;
import william.eshop.service.order.OrderService;
import william.eshop.utils.DateUtils;

/**
 * @Author zhangshenao
 * @Date 2019-12-15
 * @Description 定时任务, 关闭超时未支付的订单
 * 使用定时任务关闭订单的弊端：
 * 1. 无法在超时后立即关闭,存在时间差,程序不严谨
 * 2. 不支持分片,集群环境下会同时执行多个定时任务
 * 3. 全表扫描数据库,影响数据库性能
 * <p>
 * 解决方式:使用MQ的延迟消息
 */
@Component
@Slf4j
public class CloseTimeoutOrderTask {
    @Autowired
    private OrderService orderService;

    @Scheduled(fixedDelay = 10000L)
    public void closeTimeoutOrders() {
        log.info("Close Timeout Orders ! Current Time: {}", DateUtils.getCurrentDateString("yyyy-MM-dd hh:mm:ss"));
        orderService.allOrderStatus()
                .stream()
                .filter(OrderStatus::isTimeout)
                .forEach(o -> orderService.closeOrder(o.getOrderId()));
    }
}
