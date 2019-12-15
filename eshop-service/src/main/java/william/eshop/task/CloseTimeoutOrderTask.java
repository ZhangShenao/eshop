package william.eshop.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import william.eshop.model.OrderStatus;
import william.eshop.service.service.OrderService;
import william.eshop.utils.DateUtils;

/**
 * @Author zhangshenao
 * @Date 2019-12-15
 * @Description 定时任务, 关闭超时未支付的订单
 */
@Component
@Slf4j
public class CloseTimeoutOrderTask {
    private static final long ORDER_TIMEOUT_IN_HOURS = 2L;
    @Autowired
    private OrderService orderService;

    @Scheduled(fixedDelay = 5000L)
    public void closeTimeoutOrders() {
        log.info("Close Timeout Orders ! Current Time: {}", DateUtils.getCurrentDateString("yyyy-MM-dd hh:mm:ss"));
        orderService.allOrderStatus()
                .stream()
                .filter(this::isTimeoutOrder)
                .forEach(o -> orderService.closeOrder(o.getOrderId()));
    }

    private boolean isTimeoutOrder(OrderStatus orderStatus) {
        Date createdTime = orderStatus.getCreatedTime();
        long past = System.currentTimeMillis() - createdTime.getTime();
        return william.eshop.constants.OrderStatus.WAIT_PAY.getValue() == orderStatus.getOrderStatus()
                && past >= ORDER_TIMEOUT_IN_HOURS;
    }
}
