package william.eshop.constants;

import java.util.Arrays;

/**
 * @Author zhangshenao
 * @Date 2019-12-11
 * @Description 订单状态枚举
 */
public enum OrderStatusEnum {
    WAIT_PAY(10, "待付款"),
    WAIT_DELIVER(20, "已付款,待发货"),
    WAIT_RECEIVE(30, "已发货,待收货"),
    SUCCESS(40, "交易成功"),
    CLOSED(50, "交易关闭"),
    UNKNOWN(0, "未知");

    private int value;
    public String name;

    OrderStatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static OrderStatusEnum findByValue(int value) {
        return Arrays.stream(values()).filter(x -> x.value == value).findFirst().orElse(UNKNOWN);
    }
}
