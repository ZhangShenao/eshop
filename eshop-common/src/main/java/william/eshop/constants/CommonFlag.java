package william.eshop.constants;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description 通用状态枚举
 */
public enum CommonFlag {
    YES(1, "是"),
    NO(0, "否");

    private int value;
    private String name;

    CommonFlag(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
