package william.eshop.constants;

import java.util.Arrays;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description 支付方式枚举
 */
public enum PayMethod {
    WECHAT_PAY(1, "微信支付", true),
    ALI_PAY(2, "支付宝支付", true),
    UNKNOWN(0, "未知", false);

    private int value;

    private String name;

    private boolean supported;

    PayMethod(int value, String name, boolean supported) {
        this.value = value;
        this.name = name;
        this.supported = supported;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public boolean isSupported() {
        return supported;
    }

    public static PayMethod getByValue(int value) {
        return Arrays.stream(values()).filter(m -> m.value == value).findFirst().orElse(UNKNOWN);
    }
}
